package aust.iums.pg.admission.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
