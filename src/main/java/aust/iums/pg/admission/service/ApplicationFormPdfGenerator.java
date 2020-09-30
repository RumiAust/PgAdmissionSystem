package aust.iums.pg.admission.service;

import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.Applicant;
import aust.iums.pg.admission.model.Program;
import aust.iums.pg.admission.model.Semester;
import aust.iums.pg.admission.repository.ApplicantRepository;
import aust.iums.pg.admission.utils.PgUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
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

  @Autowired
  AdmissionHelper mHelper;

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

      cell = new PdfPCell(new Phrase("(Undergraduate) ", font14B));
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


      document.close();

        return new ByteArrayInputStream(baos.toByteArray());

    }
}
