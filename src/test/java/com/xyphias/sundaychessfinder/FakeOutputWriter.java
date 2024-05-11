package com.xyphias.sundaychessfinder;

public class FakeOutputWriter implements OutputWriter {
    public String written = "";

    @Override
    public void writeLine(String line) {
        written += line + System.lineSeparator();
    }
}
