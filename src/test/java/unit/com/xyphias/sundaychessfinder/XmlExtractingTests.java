package unit.com.xyphias.sundaychessfinder;

import com.xyphias.sundaychessfinder.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.xyphias.sundaychessfinder.XmlExtracting.extractEventsFrom;
import static common.com.xyphias.sundaychessfinder.FilePaths.resourcePathOf;
import static org.assertj.core.api.Assertions.assertThat;

public class XmlExtractingTests {
    @Test
    public void it_extracts_the_date_from_a_date_element() {
        String calendarFile = resourcePathOf("ecf-calendar-date-element.xml");

        Event event = extractEventsFrom(calendarFile).getFirst();

        assertThat(event.date()).isEqualTo(LocalDate.of(2024, 8, 18));
    }
}
