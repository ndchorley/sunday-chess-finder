package com.xyphias.sundaychessfinder;

import java.time.DayOfWeek;
import java.util.List;

import static com.xyphias.sundaychessfinder.Displaying.display;
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

}
