package testex;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter implements IDateFormatter {

  /**
   * DO NOT TEST this function as part of the exercise.
   * It's included only to get a quick way of executing the code
   * Execute to see available time format strings and responses to calling the single (non-main) public method
   */
  public static void main(String[] args) throws JokeException  {
    for (String str : TimeZone.getAvailableIDs()) {
      System.out.println(str);
    }
    
    //Executing our public method with a valid String:
    //System.out.println(new DateFormatter().getFormattedDate("Europe/Kiev"));
    
    //System.out.println(new DateFormatter().getFormattedDate("ImNotLegal"));
    
    
    
  }

  @Override
  public String getFormattedDate(String timeZone, Date time) throws JokeException {
    if(!Arrays.asList(TimeZone.getAvailableIDs()).contains(timeZone)){
      throw new JokeException("Illegal Time Zone String");
    }
    //Date time = new Date();
    String dateTimeFormat = "dd MMM yyyy hh:mm aa";
    SimpleDateFormat simpleFormat = new SimpleDateFormat(dateTimeFormat);
    simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
    return simpleFormat.format(time);
  }
}
