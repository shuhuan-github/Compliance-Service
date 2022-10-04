package com.siemens.osa.module.cs.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class TimeUtil {

    private TimeUtil() {
    }

    /**
     * change yyyy-MM-dd'T'HH:mm:ss.SSS'Z' to yyyy-MM-dd HH:mm:ss.SSS.
     *
     * @param timeStr time string
     * @return {@link String}
     */
    public static String timeCovert(String timeStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = df.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df2.format(date);
    }

    /**
     * get zero utc time.
     *
     * @param timeStr time string
     * @param utc     utc
     * @return {@link Timestamp}
     */
    public static Timestamp getZeroUtc(String timeStr, String utc) {
        Timestamp timestamp = Timestamp.valueOf(timeStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());

        calendar.add(Calendar.HOUR_OF_DAY, -1 * Integer.parseInt(utc));
        return new Timestamp(calendar.getTimeInMillis());
    }

}
