package com.example.demoasmejb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String convertLongToString(Long timeInMLS){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMLS);
        return format.format(calendar.getTime());
    }

    public static long convertStringToLong(String timeString){
        try {
            return format.parse(timeString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getCurrenTimeInMLS(){
        return Calendar.getInstance().getTimeInMillis();
    }

    public static long getTimeAfterDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTimeInMillis();
    }
}
