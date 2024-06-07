package common.com.xyphias.sundaychessfinder;

import com.xyphias.sundaychessfinder.OutputWriter;

public class FakeOutputWriter implements OutputWriter {
    public String written = "";

    @Override
    public void writeLine(String line) {
        written += line + System.lineSeparator();
    }
}
