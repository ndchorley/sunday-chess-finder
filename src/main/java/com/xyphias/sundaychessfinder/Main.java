package com.xyphias.sundaychessfinder;

import static com.xyphias.sundaychessfinder.XmlExtractor.*;

public class Main {
    public static void main(String[] args) {
        findSundayChessEvents(args[0], System.out::println);
    }

    public static void findSundayChessEvents(String calendarFile, OutputWriter outputWriter) {
        extractEventsFrom(calendarFile)
                .forEach(event -> display(event, outputWriter));
    }

    private static void display(Event event, OutputWriter outputWriter) {
        outputWriter.writeLine(event.name());
        outputWriter.writeLine("");
    }
}
