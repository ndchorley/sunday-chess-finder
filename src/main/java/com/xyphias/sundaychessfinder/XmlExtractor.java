package com.xyphias.sundaychessfinder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URI;
import java.net.URL;
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
        String name = extractName(eventElement);

        LocalDate date = extractDate(eventElement);

        URL url = extractURL(eventElement);

        return new Event(name, date, url);
    }

    private static URL extractURL(Element eventElement) {
        String urlString =
                eventElement
                        .element("properties")
                        .element("url")
                        .element("uri")
                        .getText();

        return toURL(urlString);
    }

    private static URL toURL(String urlString) {
        try {
            return new URI(urlString).toURL();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static LocalDate extractDate(Element eventElement) {
        String startDateTimeText =
                eventElement
                        .element("properties")
                        .element("dtstart")
                        .element("date-time")
                        .getText();

        LocalDate date =
                LocalDateTime
                        .parse(startDateTimeText)
                        .toLocalDate();

        return date;
    }

    private static String extractName(Element eventElement) {
        return eventElement
                .element("properties")
                .element("summary")
                .element("text")
                .getText();
    }
}
