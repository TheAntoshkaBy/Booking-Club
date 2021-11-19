package com.epam.esm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
    private final static SimpleDateFormat YEAR_FORMATTER = new SimpleDateFormat("yyyy");

    public static Date parseDate(final String parsedDate) {
        final Date date;
        try {
           date = FORMATTER.parse(parsedDate);
        } catch (final ParseException exception) {
            throw new IllegalArgumentException("Incorrect date format");
        }
        return date;
    }

    public static Date parseYear(final String parsedDate) {
        final Date date;
        try {
            date = YEAR_FORMATTER.parse(parsedDate);
        } catch (final ParseException exception) {
            throw new IllegalArgumentException("Incorrect date format");
        }
        return date;
    }

    public static String formatDate(final Date formatDate) {
        return FORMATTER.format(formatDate);
    }

    public static String formatYear(final Date formatDate) {
        return YEAR_FORMATTER.format(formatDate);
    }
}
