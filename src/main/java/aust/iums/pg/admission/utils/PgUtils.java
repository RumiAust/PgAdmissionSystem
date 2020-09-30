package aust.iums.pg.admission.utils;

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

}
