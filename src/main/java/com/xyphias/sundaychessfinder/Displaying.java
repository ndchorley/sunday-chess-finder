package com.xyphias.sundaychessfinder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Displaying {
    static void display(Event event, OutputWriter outputWriter) {
        outputWriter.writeLine(formatAsDayAndMonth(event.date()));
        outputWriter.writeLine("");
        outputWriter.writeLine(event.name());
        outputWriter.writeLine(urlOrMessageIfMissing(event));
        outputWriter.writeLine("");
    }

    private static String urlOrMessageIfMissing(Event event) {
        if (event.url() != null) {
            return event.url().toString();
        } else {
            return "No URL was provided!";
        }
    }

    private static String formatAsDayAndMonth(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMMM"));
    }
}
