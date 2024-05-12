package com.xyphias.sundaychessfinder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class XmlExtractor {
    static List<Event> extractEventsFrom(String calendarFile) {
        return
                readXml(calendarFile)
                        .getRootElement()
                        .element("vcalendar")
                        .element("components")
                        .elements("vevent")
                        .stream()
                        .map(XmlExtractor::toEvent)
                        .toList();
    }

    private static Document readXml(String calendarFile) {
        try {
            return new SAXReader().read(calendarFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Event toEvent(Element eventElement) {
        String name =
                eventElement
                        .element("properties")
                        .element("summary")
                        .element("text")
                        .getText();


        String startDateTimeText =
                eventElement
                        .element("properties")
                        .element("dtstart")
                        .element("date-time")
                        .getText();
        LocalDate startDate =
                LocalDateTime
                        .parse(startDateTimeText)
                        .toLocalDate();

        return new Event(name, startDate);
    }
}
