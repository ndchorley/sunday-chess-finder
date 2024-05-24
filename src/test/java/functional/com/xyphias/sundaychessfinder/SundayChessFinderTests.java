package functional.com.xyphias.sundaychessfinder;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.xyphias.sundaychessfinder.Finding.findSundayChessEvents;
import static common.com.xyphias.sundaychessfinder.FilePaths.resourcePathOf;
import static org.assertj.core.api.Assertions.assertThat;

public class SundayChessFinderTests {
    private final LocalDate today = LocalDate.of(2024, 6, 1);

    @Test
    public void it_displays_only_future_events_on_Sundays() {
        String calendarFile = resourcePathOf("ecf-calendar.xml");
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(calendarFile, today, fakeOutputWriter);

        assertThat(fakeOutputWriter.written)
                .isEqualTo(
                        """
                         9 June
                         
                         Wembley Sunday Rapidplay
                         https://wembley-sunday-rapidplay.co.uk
                         
                         7 July
                         
                         July Weekend Chess
                         https://july-weekend-chess.co.uk
                         
                         """
                );

    }

    @Test
    public void it_excludes_junior_events() {
        String calendarFile = resourcePathOf("ecf-calendar-with-junior-event.xml");
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(calendarFile, today, fakeOutputWriter);

        assertThat(fakeOutputWriter.written).doesNotContain("Junior Rapidplay");
    }

    @Test
    public void it_displays_a_message_if_an_event_has_no_URL() {
        String calendarFile = resourcePathOf("ecf-calendar-no-url.xml");
        FakeOutputWriter fakeOutputWriter = new FakeOutputWriter();

        findSundayChessEvents(calendarFile, today, fakeOutputWriter);

        assertThat(fakeOutputWriter.written)
                .isEqualTo(
                        """
                         18 August
                         
                         London Rapidplay
                         No URL was provided!
                         
                         """
                );
    }
}
