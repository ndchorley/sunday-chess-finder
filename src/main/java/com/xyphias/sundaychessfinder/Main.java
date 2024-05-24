package com.xyphias.sundaychessfinder;

import java.time.LocalDate;

import static com.xyphias.sundaychessfinder.Finding.findSundayChessEvents;

public class Main {
    public static void main(String[] args) {
        String calendarFile = args[0];
        LocalDate today = LocalDate.now();

        findSundayChessEvents(calendarFile, today, System.out::println);
    }
}
