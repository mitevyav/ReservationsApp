package com.example.yavor.reservations.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {

    private static final String DATE_FORMAT = "d MMM hh:mm";


    private Utils() {

    }


    public static String formatDateForPresentation(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String strDate = format.format(date);
        return strDate;
    }
}
