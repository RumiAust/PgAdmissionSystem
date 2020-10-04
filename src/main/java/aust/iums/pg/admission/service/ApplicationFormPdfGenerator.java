package aust.iums.pg.admission.service;

import aust.iums.pg.admission.enums.AdmissionEnum;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.ApplicantPersonalInfoRepository;
import aust.iums.pg.admission.repository.ApplicantRepository;
import aust.iums.pg.admission.utils.PgUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ApplicationFormPdfGenerator {
      @Value("${pgAdmission.file.image.photo-destination}")
      String apPhotoBasePath;

      @Value("${pgAdmission.file.image.signature-destination}")
      String apSignatureBasePath;

  String additionalPath;
  boolean isSerialNoExists;
  Boolean isDraft;
  boolean isFacultyEngg=false;
  private final  Logger log = LoggerFactory.getLogger(ApplicationFormPdfGenerator.class);

  private final AdmissionHelper mHelper;
  private final ApplicantPersonalInfoRepository applicantPersonalInfoRepository;

    public ApplicationFormPdfGenerator(AdmissionHelper mHelper, ApplicantPersonalInfoRepository applicantPersonalInfoRepository) {
        this.mHelper = mHelper;
        this.applicantPersonalInfoRepository = applicantPersonalInfoRepository;
    }

    public ByteArrayInputStream createApplicationForm(String applicationSn, String dateOfBirth ) throws DocumentException, IOException, ParseException {

      log.info(" [{}]: Applicant form pdf generation for aust admission test Starts ",applicationSn);
      this.isDraft = isDraft;
      Font font11R = new Font(Font.FontFamily.HELVETICA, 10.5f, Font.NORMAL);
      Font font11B = new Font(Font.FontFamily.HELVETICA, 10.5f, Font.BOLD);
      Font font10B = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
      Font font10R = new Font(Font.FontFamily.HELVETICA, 8.5f, Font.NORMAL);
      Font font12R = new Font(Font.FontFamily.HELVETICA, 11.5f, Font.NORMAL);
      Font font14R = new Font(Font.FontFamily.HELVETICA, 13.5f, Font.NORMAL);
      Font font14B = new Font(Font.FontFamily.HELVETICA, 13.5f, Font.BOLD);
      Font font17B = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
      Font font18B = new Font(Font.FontFamily.HELVETICA, 17.5f, Font.BOLD);
      Font font19B = new Font(Font.FontFamily.HELVETICA, 19, Font.BOLD);
      Font font8R = new Font(Font.FontFamily.HELVETICA,  7, Font.NORMAL);
      Font fontSchoolNameR = new Font(Font.FontFamily.HELVETICA, 9.0f, Font.NORMAL);
      int minimumPersonalInfoRowHeight = 26;


        Date dob = PgUtils.formateDate(dateOfBirth);
        Optional<Applicant> applicant = mHelper.getApplicantBy(applicationSn,dob);
        Long semesterId=applicant.get().getSemesterId();
        Optional<Semester> semester= mHelper.getSemesterById(semesterId);
        additionalPath = semester.get().getSemesterName().replaceAll(" ", "-").toLowerCase();
        Long programId=applicant.get().getProgramId();
        Optional<Program>program=mHelper.getProgramById(programId);
        additionalPath=additionalPath+"/"+program.get().getProgramShortName();
        String photoBasePath, signatureBasePath;
        photoBasePath = apPhotoBasePath + additionalPath + "/" + applicationSn+ ".jpg";
        signatureBasePath = apSignatureBasePath + additionalPath + "/" + applicationSn + ".jpg";


        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
      writer.setPageEvent(new ApplicationFormFooter());
        document.open();
        document.setPageSize(PageSize.A4);

      Paragraph paragraph = new Paragraph();
      PdfPCell cell = new PdfPCell();
      Chunk chunk = new Chunk();

      final ClassLoader classloader = this.getClass().getClassLoader();
      String aust = "logo-aust.png";
      final URL austLogoUrl = classloader.getResource("static/" + aust);

      PdfPTable h2 = new PdfPTable(1);
      h2.setSpacingBefore(5);
      h2.setSpacingAfter(5);
      h2.setWidthPercentage(100);
      PdfPTable header = new PdfPTable(new float[]{.15f,.15f,.2f,.2f,.2f,.2f});
      header.setSpacingBefore(5);
      header.setSpacingAfter(5);
      header.setWidthPercentage(100);

      Image austLogo = Image.getInstance(austLogoUrl);
      cell = new PdfPCell(austLogo, true);
      cell.setBorder(0);
      cell.setPadding(2);
      cell.setRowspan(3);
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);
      cell.setUseAscender(true);
      cell.setFixedHeight(60);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      header.addCell(cell);

      cell = new PdfPCell(new Phrase("    Ahsanullah University of Science and Technology", font18B));
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      cell.setBorder(0);
      cell.setColspan(5);
      header.addCell(cell);

      cell = new PdfPCell(new Phrase("(Approved by the Government of the People’s Republic of Bangladesh and Sponsored by the Dhaka Ahsania Mission)", font10R));
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      cell.setBorder(0);
      cell.setColspan(5);
      header.addCell(cell);

      cell = new PdfPCell(new Phrase("141-142 Love Road, Tejgaon Industrial Area, Dhaka-1208", font12R));
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      cell.setBorder(0);
      cell.setColspan(5);
      header.addCell(cell);

      cell = new PdfPCell(header);
      cell.setBorder(0);
      h2.addCell(cell);
      document.add(h2);

      //
      PdfPTable pInfoOuter = new PdfPTable(1);
      pInfoOuter.setWidthPercentage(100);
      PdfPTable appFrom = new PdfPTable(5);
      appFrom.setWidthPercentage(100);


      cell = new PdfPCell(new Phrase("APPLICATION FORM FOR ADMISSION", font19B));
      cell.setBorder(0);
      cell.setColspan(4);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appFrom.addCell(cell);

      //applicant image
      Image photo = Image.getInstance(photoBasePath);
      cell = new PdfPCell(photo, true);
      cell.setPadding(2);
      //  cell.setBorder(0);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      cell.setRowspan(5);
      appFrom.addCell(cell);

      cell = new PdfPCell(new Phrase("INTO MASTER PROGRAM ", font14B));
      cell.setBorder(0);
      cell.setMinimumHeight(25);
      cell.setColspan(4);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appFrom.addCell(cell);

      cell = new PdfPCell(new Phrase("" + semester.get().getSemesterName() + " SEMESTER ", font17B));
      cell.setBorder(0);
      cell.setColspan(4);
      cell.setMinimumHeight(30);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appFrom.addCell(cell);

      //inner table of applicant serial No
      PdfPTable appSerialNo = new PdfPTable(5 + applicationSn.length());
      appSerialNo.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase(" ", font19B));
      cell.setBorder(0);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appSerialNo.addCell(cell);

      cell = new PdfPCell(new Phrase("Application No:", font14R));
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appSerialNo.addCell(cell);

      char[] ch = applicationSn.toCharArray();
      for (char c : ch) {
        cell = new PdfPCell(new Phrase("" + c, font19B));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        appSerialNo.addCell(cell);
      }

      cell = new PdfPCell(new Phrase(" ", font19B));
      cell.setBorder(0);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appSerialNo.addCell(cell);


      cell = new PdfPCell(appSerialNo);
      cell.setColspan(4);
      cell.setBorder(0);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      appFrom.addCell(cell);


      cell = new PdfPCell(appFrom);
      cell.setBorder(0);
      pInfoOuter.addCell(cell);
      document.add(pInfoOuter);

      //

      chunk = new Chunk(" ", font14B);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_LEFT);
      paragraph.add(chunk);
      document.add(paragraph);

      PdfPTable programName = new PdfPTable(1);
      h2.setSpacingBefore(5);
      h2.setSpacingAfter(5);
      h2.setWidthPercentage(100);
      cell = new PdfPCell(new Phrase("Program : "+program.get().getProgramLongName(), font19B));
      cell.setBorder(0);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      programName.addCell(cell);
      document.add(programName);

      chunk = new Chunk(" ", font14B);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_LEFT);
      paragraph.add(chunk);
      document.add(paragraph);

      //personal info

      // pinfo test
      DottedLineSeparator dottedline = new DottedLineSeparator();
      dottedline.setOffset(-4);
      dottedline.setGap(5f);

      float[] cWidths = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2,2};
      PdfPTable pInfo = new PdfPTable(cWidths);
      pInfo.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("1. Full Name (as in SSC/GCE ‘O’ Level)", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(5);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      ApplicantPersonaIInfo applicantPersonaIInfo = applicantPersonalInfoRepository.getByApplicant_Id(applicant.get().getId());
      String fName=applicantPersonaIInfo.getFirstName().toUpperCase();
      String  mName=applicantPersonaIInfo.getMiddleName() ==null ? " ":applicantPersonaIInfo.getMiddleName().toUpperCase();
      String lName=applicantPersonaIInfo.getLastName()==null ? " ":applicantPersonaIInfo.getLastName().toUpperCase();
      String  fullName=fName+mName+lName;
      paragraph= new Paragraph(""+fullName, font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(6);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("2. Father’s Name", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getFatherName().toUpperCase(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(9);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("3. Mother’s Name", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getMotherName().toUpperCase(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(9);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);


      cell = new PdfPCell(new Phrase("4. Gender", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getGender(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase("5. Religion", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getReligion(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);


      cell = new PdfPCell(new Phrase("6. Nationality", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getNationality(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      String birthDate=PgUtils.dateToString(applicantPersonaIInfo.getDateOfBirth());
      cell = new PdfPCell(new Phrase("7. Date of Birth", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+birthDate, font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("8. Place of Birth", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getPlaceOfBirth()==null ? " ":applicantPersonaIInfo.getPlaceOfBirth(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(9);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);


      cell = new PdfPCell(new Phrase("9. Mobile Number", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getMobileNumber(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase("10.Email Address", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(""+applicantPersonaIInfo.getEmailAddress(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);

       List<ApplicantAddress> applicantAddressList=applicant.get().getApplicantAddresses().stream().sorted(Comparator.comparing(ApplicantAddress::getAddressType).reversed()).collect(Collectors.toList());

      cell = new PdfPCell(new Phrase("11. Present Address", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(applicantAddressList.get(0).getLine1()==null ? " ": applicantAddressList.get(0).getLine1(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(8);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      String divisionName=applicantAddressList.get(0).getDivision().getDivisionName();
      String districtName=applicantAddressList.get(0).getDistrict().getDistrictName();
      String thanaName="";
      String t=applicantAddressList.get(0).getThanaOther();
      if(applicantAddressList.get(0).getThanaOther() ==null){
        thanaName=applicantAddressList.get(0).getThana().getThanaName();
      }else{
        thanaName=applicantAddressList.get(0).getThanaOther();
      }

      paragraph= new Paragraph(""+divisionName+","+districtName+","+thanaName, font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(12);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("12. Permanent Address", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(3);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      cell = new PdfPCell(new Phrase(":", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      pInfo.addCell(cell);
      paragraph= new Paragraph(applicantAddressList.get(1).getLine1()==null ? " ": applicantAddressList.get(1).getLine1(), font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(8);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      divisionName=applicantAddressList.get(1).getDivision().getDivisionName();
      districtName=applicantAddressList.get(1).getDistrict().getDistrictName();
      thanaName="";
      if(applicantAddressList.get(1).getThanaOther() ==null){
        thanaName=applicantAddressList.get(1).getThana().getThanaName();
      }else{
        thanaName=applicantAddressList.get(1).getThanaOther();
      }

      paragraph= new Paragraph(""+divisionName+", "+districtName+", "+thanaName, font11R);
      paragraph.add(dottedline);
      cell = new PdfPCell(paragraph);
      cell.setBorder(0);
      cell.setMinimumHeight(minimumPersonalInfoRowHeight);
      cell.setColspan(12);//…
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      pInfo.addCell(cell);
      document.add(pInfo);



      //edu
      List<ApplicantEducationalInfo> eduInfoList=applicant.get().getApplicantEducationalInfo();
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);

      Chunk  chunkNo = new Chunk("13.  ", font11R);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);

      Chunk chunkDetails = new Chunk("\tEducational Qualifications: ", font11B);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);

      paragraph = new Paragraph();
      paragraph.add(chunkNo);
      paragraph.add(chunkDetails);
      document.add(paragraph);


      //education table

      PdfPTable educationInfo = new PdfPTable(16);
      educationInfo.setSpacingBefore(5);
      educationInfo.setSpacingAfter(5);
      educationInfo.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("Examinations", font11B));
      cell.setMinimumHeight(20);
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("School/College/Institution", font11B));
      cell.setColspan(4);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Board/Institution", font11B));
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Total Marks/Grade", font11B));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Division/Class/Grade", font11B));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Year of Passing", font11B));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      //ssc

      cell = new PdfPCell(new Phrase("SSC or Equivalent", fontSchoolNameR));
      cell.setMinimumHeight(20);
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase(""+eduInfoList.get(0).getInstituteName() , fontSchoolNameR));
      cell.setColspan(4);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(0).getBoard(), fontSchoolNameR));
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(0).getTotalMarks(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(0).getDivisionClassGrade(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(0).getPassingYear(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      //hsc

      cell = new PdfPCell(new Phrase("HSC or Equivalent", fontSchoolNameR));
      cell.setMinimumHeight(20);
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase(""+eduInfoList.get(1).getInstituteName() , fontSchoolNameR));
      cell.setColspan(4);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(1).getBoard(), fontSchoolNameR));
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(1).getTotalMarks(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(1).getDivisionClassGrade(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(1).getPassingYear(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      //bsc

      cell = new PdfPCell(new Phrase("BBA/B.Com/B.Sc./B.A./ Equivalent", fontSchoolNameR));
      cell.setMinimumHeight(20);
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase(""+eduInfoList.get(2).getInstituteName() , fontSchoolNameR));
      cell.setColspan(4);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(2).getBoard(), fontSchoolNameR));
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(2).getTotalMarks(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(2).getDivisionClassGrade(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(2).getPassingYear(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      //msc

      cell = new PdfPCell(new Phrase("M.Com/M.A./MBA/M.Sc.", fontSchoolNameR));
      cell.setMinimumHeight(20);
      cell.setColspan(3);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase(""+eduInfoList.get(3).getInstituteName()  ==null? " ":eduInfoList.get(3).getInstituteName(), fontSchoolNameR));
      cell.setColspan(4);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(3).getBoard()==null? " ":eduInfoList.get(3).getBoard(), fontSchoolNameR));
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(3).getTotalMarks()==null? " ":eduInfoList.get(3).getTotalMarks(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("" + eduInfoList.get(3).getDivisionClassGrade()==null ? " ":eduInfoList.get(3).getDivisionClassGrade(), fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);
      Integer mscYear=eduInfoList.get(3).getPassingYear();
      String yr="";
      if(mscYear==null){
        yr=" ";
      }else {
        yr=mscYear.toString();
      }

      cell = new PdfPCell(new Phrase("" + yr, fontSchoolNameR));
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      educationInfo.addCell(cell);

      document.add(educationInfo);





      //
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);


      //job
      List<JobExperience> jobExperienceList=applicant.get().getJobExperience();
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);

       chunkNo = new Chunk("14.  ", font11R);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);

      chunkDetails = new Chunk("\tJob Experience ", font11B);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);

      paragraph = new Paragraph();
      paragraph.add(chunkNo);
      paragraph.add(chunkDetails);
      document.add(paragraph);

      PdfPTable jobInfo = new PdfPTable(14);
      jobInfo.setSpacingBefore(5);
      jobInfo.setSpacingAfter(5);
      jobInfo.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("Name of the organization/Employer", font11B));
      cell.setMinimumHeight(20);
      cell.setColspan(4);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      jobInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Designation", font11B));
      cell.setMinimumHeight(20);
      cell.setColspan(2);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      jobInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Job Responsibilty", font11B));
      cell.setMinimumHeight(20);
      cell.setColspan(4);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      jobInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Duration", font11B));
      cell.setMinimumHeight(20);
      cell.setColspan(4);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      jobInfo.addCell(cell);

      if(jobExperienceList.size()>0){
        for(int i=0;i<jobExperienceList.size();i++) {
          cell = new PdfPCell(new Phrase("" + jobExperienceList.get(i).getOrganizationName(), fontSchoolNameR));
          cell.setMinimumHeight(20);
          cell.setColspan(4);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setUseAscender(true);
          cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
          jobInfo.addCell(cell);

          cell = new PdfPCell(new Phrase(""+jobExperienceList.get(i).getDesignation(), fontSchoolNameR));
          cell.setMinimumHeight(20);
          cell.setColspan(2);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setUseAscender(true);
          cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
          jobInfo.addCell(cell);

          cell = new PdfPCell(new Phrase(""+jobExperienceList.get(i).getJobResponsibilities(), fontSchoolNameR));
          cell.setMinimumHeight(20);
          cell.setColspan(4);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setUseAscender(true);
          cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
          jobInfo.addCell(cell);

          String fromDate=PgUtils.dateToString(jobExperienceList.get(i).getFromDate());
          String toDate=PgUtils.dateToString(jobExperienceList.get(i).getToDate());
          cell = new PdfPCell(new Phrase(fromDate+" To "+toDate, fontSchoolNameR));
          cell.setMinimumHeight(20);
          cell.setColspan(4);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setUseAscender(true);
          cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
          jobInfo.addCell(cell);
        }
      }

      document.add(jobInfo);



      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);

      chunkNo = new Chunk("15.  ", font11R);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);

      chunkDetails = new Chunk("\tDeclaration: ", font11B);
      paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_CENTER);
      paragraph.add(chunk);


      paragraph = new Paragraph();
      paragraph.add(chunkNo);
      paragraph.add(chunkDetails);
      document.add(paragraph);

      chunk = new Chunk(" ");
      paragraph=new Paragraph(chunk);
      document.add(paragraph);


      //dec

      paragraph= new Paragraph("I do hereby declare that the information as stated in the Admission application form is true. If any of the information is found incorrect, my admission is liable to be cancelled.", font11R);
      // paragraph.setLeading(18);
      paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
      document.add(paragraph);

      paragraph= new Paragraph("If any of the certificates/academic transcripts/documents attached with this form is found altered/forged, my admission is also liable to be cancelled.", font11R);
      paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

      document.add(paragraph);

      paragraph= new Paragraph("I also declare that I will abide by all the existing as well as any new rules and regulations of the University, if made in future, and the orders of the AUST authority; otherwise my admission is liable to be cancelled.", font11R);
      paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
      document.add(paragraph);



      PdfPTable signatureInfo = new PdfPTable(4);
      signatureInfo.setSpacingBefore(5);
      signatureInfo.setSpacingAfter(5);
      signatureInfo.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase(" ", font11R));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setRowspan(3);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      signatureInfo.addCell(cell);


      //signature
      Image signature = Image.getInstance(signatureBasePath);
      cell = new PdfPCell(signature, true);
      cell.setPadding(2);
      cell.setBorder(0);
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);
      cell.setFixedHeight(30);
      signatureInfo.addCell(cell);

      cell = new PdfPCell(new Phrase("Signature of the Applicant", font10B));
      cell.setBorder(0);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      signatureInfo.addCell(cell);
      //
      LocalDateTime myDateObj = LocalDateTime.now();
      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
      String formattedDate = myDateObj.format(myFormatObj);

      PdfPTable date = new PdfPTable(new float[]{.1f,.1f,.2f});
      date.setWidthPercentage(100);
      dottedline = new DottedLineSeparator();
      dottedline.setOffset(-2);
      dottedline.setGap(5f);
      paragraph= new Paragraph("Date :", font11R);
      cell = new PdfPCell(new Phrase(paragraph));
      cell.setBorder(0);
      date.addCell(cell);
      paragraph= new Paragraph(""+formattedDate, font11R);
      cell = new PdfPCell(new Phrase(paragraph));
      cell.setColspan(2);
      cell.setBorder(0);
      date.addCell(cell);

      //
      cell = new PdfPCell(date);
      cell.setBorder(0);
      cell.setMinimumHeight(20);
      signatureInfo.addCell(cell);

      document.add(signatureInfo);


      // office use only

      //line separator
      document.add(new LineSeparator());

      //office use

      //Student ID Info
      float[] columnWidths = {6, 2, 2, 2, 2, 2, 2, 2, 2, 2};
      PdfPTable table = new PdfPTable(columnWidths);
      table.setSpacingBefore(5);
      table.setSpacingAfter(5);

      cell = new PdfPCell(new Phrase("Student ID ", font14B));
      cell.setMinimumHeight(20);
      cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
      cell.setUseAscender(true);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      //cell.setBorder(0);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      //cell.setBorder(0);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(" ", font10B));
      //cell.setBorder(0);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      table.addCell(cell);
      document.add(table);

      // office heading
      PdfPTable officeHeading = new PdfPTable(1);
      officeHeading.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("FOR OFFICE USE ONLY", font14B));
      cell.setBorder(0);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      officeHeading.addCell(cell);
      cell = new PdfPCell(new Phrase("(DO NOT WRITE ANYTHING BELOW THIS LINE)", font11B));
      cell.setBorder(0);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      officeHeading.addCell(cell);
      document.add(officeHeading);

      chunk = new Chunk(" ");
      paragraph=new Paragraph(chunk);
      document.add(paragraph);

      // office part of form

      PdfPTable officePart = new PdfPTable(new float[]{.24f,.15f,.2f});
      officePart.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("Name of the applicant (As in SSC/O-Level ):", font11R));
      cell.setBorder(0);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officePart.addCell(cell);
      cell = new PdfPCell(new Phrase("……………………………………………………………………………", font11R));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officePart.addCell(cell);
      cell = new PdfPCell(new Phrase("The student bearing the above particulars with Merit Position: ……… Payment TransactionID:……………………", font11R));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officePart.addCell(cell);

        if(applicant.get().getStatus()== AdmissionEnum.ADMISSION_OFFERED.name()) {
          chunkNo = new Chunk("admission in the program ", font11R);
          chunkDetails = new Chunk("" + program.get().getProgramLongName(), font11B);
          chunkDetails.setUnderline(0.1f, -2f);
          chunk = new Chunk(" in the First Year First Semester (" + semester.get().getSemesterName() + ")", font11R);
          paragraph = new Paragraph();
          paragraph.add(chunkNo);
          paragraph.add(chunkDetails);
          paragraph.add(chunk);
          cell = new PdfPCell(new Phrase(paragraph));
          cell.setBorder(0);
          cell.setColspan(3);
          cell.setMinimumHeight(25);
          cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
          officePart.addCell(cell);
        }


      document.add(officePart);

      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);
      for (int i=0;i<4;i++){
        chunk = new Chunk(" ");
        paragraph= new Paragraph(chunk);
        document.add(paragraph);
      }

      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);
      chunk = new Chunk(" ");
      paragraph= new Paragraph(chunk);
      document.add(paragraph);


      //Office Signature part
      PdfPTable officeFooter = new PdfPTable(7);
      officeFooter.setWidthPercentage(100);

      cell = new PdfPCell(new Phrase("Verified By:…………………", font11R));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);
      cell = new PdfPCell(new Phrase("_________________________________", font11B));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);
      cell = new PdfPCell(new Phrase("_______________________", font11B));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setMinimumHeight(20);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);

      cell = new PdfPCell(new Phrase("(Verification Committee)", font11R));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);

      cell = new PdfPCell(new Phrase("Signature of the Admission\nCommittee’s Chairman", font11B));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);

      cell = new PdfPCell(new Phrase("Signature of the Registrar", font11B));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setMinimumHeight(25);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);



      cell = new PdfPCell(new Phrase("Date\t:\t……………………", font11R));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);


      cell = new PdfPCell(new Phrase("Date\t:\t………………………", font11R));
      cell.setBorder(0);
      cell.setColspan(3);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);

      cell = new PdfPCell(new Phrase("Date\t:\t……………………", font11R));
      cell.setBorder(0);
      cell.setColspan(2);
      cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
      officeFooter.addCell(cell);
      document.add(officeFooter);






      document.close();

        return new ByteArrayInputStream(baos.toByteArray());

    }

  public class ApplicationFormFooter extends PdfPageEventHelper{

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
      //  super.onStartPage(writer, document);
      LocalDateTime myDateObj = LocalDateTime.now();
      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy");
      String formattedDate = myDateObj.format(myFormatObj);
      String documentRefNo="";
      documentRefNo=formattedDate+"/AUST/PG/00";
      /*if(isFacultyEngg){
        documentRefNo=formattedDate+"/AUST/UG/01";
      }else{
        documentRefNo=formattedDate+"/AUST/UG/02";
      }*/
      PdfContentByte cb = writer.getDirectContent();
      ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(PgUtils.getHeaderParagraph(documentRefNo)),
          document.right() + 10, document.top() + 10, 0);

    }
  }

}


