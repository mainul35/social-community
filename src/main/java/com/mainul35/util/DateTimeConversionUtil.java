package com.mainul35.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConversionUtil {
    public Timestamp convertStringToTimestamp(String dateTime, String format) {
        Timestamp timestamp = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            Date parsedDate = dateFormat.parse(dateTime);
            return timestamp = new Timestamp(parsedDate.getTime());
        } catch (Exception e) { // this generic but you can control another types of exception
            // look the origin of excption
            e.printStackTrace();
        }
        return timestamp;
    }

    public String convertTimestampToString(Timestamp timestamp, String format) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat(format).format(date);
        return formattedDate;
    }
}
