package com.xyphias.sundaychessfinder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.xyphias.sundaychessfinder.XmlExtractor.extractEventsFrom;

public class Main {
    public static void main(String[] args) {
        String calendarFile = args[0];

        findSundayChessEvents(calendarFile, System.out::println);
    }

    public static void findSundayChessEvents(String calendarFile, OutputWriter outputWriter) {
        List<Event> allEvents = extractEventsFrom(calendarFile);

        onlyEventsOnSunday(allEvents).forEach(event -> display(event, outputWriter));
    }

    private static List<Event> onlyEventsOnSunday(List<Event> allEvents) {
        return allEvents.stream()
                .filter(event -> event.date().getDayOfWeek() == DayOfWeek.SUNDAY)
                .toList();
    }

    private static void display(Event event, OutputWriter outputWriter) {
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
