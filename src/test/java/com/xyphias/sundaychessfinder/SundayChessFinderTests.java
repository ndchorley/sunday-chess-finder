package com.xyphias.sundaychessfinder;

import org.junit.jupiter.api.Test;

import static com.xyphias.sundaychessfinder.Main.findSundayChessEvents;
import static org.assertj.core.api.Assertions.assertThat;

public class SundayChessFinderTests {
    @Test
    public void it_displays_only_events_on_Sundays() {
        String calendarFile = resourcePathOf("ecf-calendar.xml");
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(calendarFile, fakeOutputWriter);

        assertThat(fakeOutputWriter.written)
                .isEqualTo(
                        """
                         12 May
                         
                         London Rapidplay
                         https://london-rapidplay.com
                         
                         9 June
                         
                         Wembley Sunday Rapidplay
                         https://wembley-sunday-rapidplay.co.uk
                         
                         7 July
                         
                         July Weekend Chess
                         https://july-weekend-chess.co.uk
                         
                         """
                );

    }

    private String resourcePathOf(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
