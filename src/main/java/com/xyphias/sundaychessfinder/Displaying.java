package com.xyphias.sundaychessfinder;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Displaying {
    public static void display(Event event, OutputWriter outputWriter) {
        outputWriter.writeLine(formatAsDayAndMonth(event.date()));
        outputWriter.writeLine("");
        outputWriter.writeLine(event.name());
        outputWriter.writeLine(urlOrMessageIfMissing(event.url()));
        outputWriter.writeLine("");
    }

    private static String formatAsDayAndMonth(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMMM"));
    }

    private static String urlOrMessageIfMissing(URL url) {
        if (url != null) {
            return url.toString();
        } else {
            return "No URL was provided!";
        }
    }
}
