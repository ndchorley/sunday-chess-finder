package com.xyphias.sundaychessfinder;

import static com.xyphias.sundaychessfinder.Finding.findSundayChessEvents;

public class Main {
    public static void main(String[] args) {
        String calendarFile = args[0];

        findSundayChessEvents(calendarFile, System.out::println);
    }
}
