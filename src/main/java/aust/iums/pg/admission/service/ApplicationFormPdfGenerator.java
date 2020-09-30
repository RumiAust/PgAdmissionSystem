package aust.iums.pg.admission.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@Service
public class ApplicationFormPdfGenerator {
    public ByteArrayInputStream createApplicationForm(String applicationSn, String dateOfBirth ) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        document.setPageSize(PageSize.A4);
        Font font12R = new Font(Font.FontFamily.HELVETICA, 11.5f, Font.NORMAL);

        document.addTitle("Testimonial");
        Paragraph paragraph = new Paragraph();
        PdfPCell cell = new PdfPCell();
        Chunk chunk = new Chunk();
        paragraph.add("minhaz");
        document.add(paragraph);


        document.close();

        return new ByteArrayInputStream(baos.toByteArray());

    }
}
