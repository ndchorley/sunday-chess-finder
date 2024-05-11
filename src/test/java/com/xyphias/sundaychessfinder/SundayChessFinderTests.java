package com.xyphias.sundaychessfinder;

import org.junit.jupiter.api.Test;

import static com.xyphias.sundaychessfinder.Main.findSundayChessEvents;
import static org.assertj.core.api.Assertions.assertThat;

public class SundayChessFinderTests {
    @Test
    public void it_displays_all_the_events() {
        String calendarFile = resourcePathOf("ecf-calendar.xml");
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(calendarFile, fakeOutputWriter);

        assertThat(fakeOutputWriter.written)
                .isEqualTo(
                        """                                
                         Muswell Hill FIDE Chess 2024
                         
                         London Rapidplay
                         
                         June Weekday Chess
                         
                         Wembley Sunday Rapidplay
                         
                         July Weekend Chess
                         
                         """
                );

    }

    private String resourcePathOf(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
