package unit.com.xyphias.sundaychessfinder;

import com.xyphias.sundaychessfinder.Event;
import common.com.xyphias.sundaychessfinder.FakeOutputWriter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.xyphias.sundaychessfinder.Displaying.display;
import static org.assertj.core.api.Assertions.assertThat;

public class DisplayingTests {
    @Test
    public void a_message_is_provided_if_an_event_has_no_URL() {
        Event event =
                new Event(
                        "London Rapidplay",
                        LocalDate.of(2024, 10, 1),
                        null,
                        false
                );

        FakeOutputWriter outputWriter = new FakeOutputWriter();

        display(event, outputWriter);

        assertThat(outputWriter.written).endsWith("No URL was provided!\n\n");
    }
}
