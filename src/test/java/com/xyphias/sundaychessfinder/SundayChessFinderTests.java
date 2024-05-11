package com.xyphias.sundaychessfinder;

import org.junit.jupiter.api.Test;

import static com.xyphias.sundaychessfinder.Main.findSundayChessEvents;
import static org.assertj.core.api.Assertions.assertThat;

public class SundayChessFinderTests {
    @Test
    public void it_displays_a_message() {
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(fakeOutputWriter);

        assertThat(fakeOutputWriter.written).isEqualTo("Sunday chess events finder\n");
    }
}
