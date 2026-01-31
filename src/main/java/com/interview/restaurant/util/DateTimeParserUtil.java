package com.interview.restaurant.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class DateTimeParserUtil {

    private DateTimeParserUtil() {
        // private constructor to prevent instantiation
    }

    public static final DateTimeFormatter customDateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("[h:mm a]") // Handles 10:00 AM (with space)
                .appendPattern("[h:mma]")  // Handles 10:00AM (no space)
                .appendPattern("[H:mm]")   // Handles 10:00 (24h, defaults to AM)
                .toFormatter(Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.STRICT);

}
