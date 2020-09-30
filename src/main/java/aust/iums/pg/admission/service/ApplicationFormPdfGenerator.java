package aust.iums.pg.admission.service;

import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.Applicant;
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

      if (applicationSn!= null && dateOfBirth != null) {
        Date dob = PgUtils.formateDate(dateOfBirth);
        Optional<Applicant> applicant = mHelper.getApplicantBy(applicationSn,dob);
        Long semesterId=applicant.get().getSemesterId();
       Optional<Semester> semester= mHelper.getSemesterById(semesterId);
        additionalPath = semester.get().getSemesterName().replaceAll(" ", "-").toLowerCase();
      }


        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        document.setPageSize(PageSize.A4);

        Paragraph paragraph = new Paragraph();
        PdfPCell cell = new PdfPCell();
        Chunk chunk = new Chunk();
        paragraph.add("minhaz");
        document.add(paragraph);


        document.close();

        return new ByteArrayInputStream(baos.toByteArray());

    }
}
