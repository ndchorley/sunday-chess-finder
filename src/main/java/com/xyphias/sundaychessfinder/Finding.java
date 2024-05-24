package com.xyphias.sundaychessfinder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.xyphias.sundaychessfinder.Displaying.display;
import static com.xyphias.sundaychessfinder.XmlExtracting.extractEventsFrom;

public class Finding {
    public static void findSundayChessEvents(String calendarFile, LocalDate today, OutputWriter outputWriter) {
        List<Event> allEvents = extractEventsFrom(calendarFile);

        onlyEventsOnSunday(allEvents).stream()
                .sorted(Comparator.comparing(Event::date))
                .filter(event -> event.date().isAfter(today))
                .filter(event -> !event.isJunior())
                .forEach(event -> display(event, outputWriter));
    }

    private static List<Event> onlyEventsOnSunday(List<Event> allEvents) {
        return allEvents.stream()
                .filter(event -> event.date().getDayOfWeek() == DayOfWeek.SUNDAY)
                .toList();
    }
}
