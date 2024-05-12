package com.xyphias.sundaychessfinder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.xyphias.sundaychessfinder.XmlExtractor.*;

public class Main {
    public static void main(String[] args) {
        String calendarFile = args[0];

        findSundayChessEvents(calendarFile, System.out::println);
    }

    public static void findSundayChessEvents(String calendarFile, OutputWriter outputWriter) {
        extractEventsFrom(calendarFile)
                .forEach(event -> display(event, outputWriter));
    }

    private static void display(Event event, OutputWriter outputWriter) {
        outputWriter.writeLine(formatAsDayAndMonth(event.date()));
        outputWriter.writeLine("");
        outputWriter.writeLine(event.name());
        outputWriter.writeLine(event.url().toString());
        outputWriter.writeLine("");
    }

    private static String formatAsDayAndMonth(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMMM"));
    }
}
