package com.xyphias.sundaychessfinder;

public class Main {
    public static void main(String[] args) {
        findSundayChessEvents(System.out::println);
    }

    public static void findSundayChessEvents(OutputWriter outputWriter) {
        outputWriter.writeLine("Sunday chess events finder");
    }
}
