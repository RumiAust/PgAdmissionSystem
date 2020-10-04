package aust.iums.pg.admission.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Monjur-E-Morshed on 9/21/2020.
 */
public class PgUtils {

  public static Date formateDate(String pDate) throws ParseException {
    DateFormat formatter;
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd");
    date = formatter.parse(pDate);
    return date;
  }

  public static  String instantFormatter(Instant pInstant){
    Date myDate = Date.from(pInstant);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    String output = formatter.format(myDate);
    return output;
  }
  public static String dateToString(Date pDate){
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = dateFormat.format(pDate);
    return strDate;
  }
  public static Paragraph getHeaderParagraph(String pInfo) {
    Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 6.0f, Font.BOLDITALIC, BaseColor.GRAY);
    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
    Paragraph ugHeaderParagraph =
        new Paragraph(pInfo, headerFont);
    ugHeaderParagraph.setAlignment(Element.ALIGN_RIGHT);
    return ugHeaderParagraph;
  }

}
