package com.xyphias.sundaychessfinder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class XmlExtracting {
    public static List<Event> extractEventsFrom(String calendarFile) {
        return
                readXml(calendarFile)
                        .getRootElement()
                        .element("vcalendar")
                        .element("components")
                        .elements("vevent")
                        .stream()
                        .map(XmlExtracting::toEvent)
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

        boolean isJunior = extractIsJunior(eventElement);

        return new Event(name, date, url, isJunior);
    }

    private static String extractName(Element eventElement) {
        return eventElement
                .element("properties")
                .element("summary")
                .element("text")
                .getText();
    }

    private static LocalDate extractDate(Element eventElement) {
        Element dtStartElement =
                eventElement
                        .element("properties")
                        .element("dtstart");

        return
                extractDateFromDateElement(dtStartElement)
                        .orElseGet(() -> extractDateFromDateTimeElement(dtStartElement));

    }

    private static Optional<LocalDate> extractDateFromDateElement(Element dtStartElement) {
        Element dateElement = dtStartElement.element("date");

        if (dateElement == null) {
            return Optional.empty();
        }

        return Optional.of(LocalDate.parse(dateElement.getText()));
    }

    private static LocalDate extractDateFromDateTimeElement(Element dtStartElement) {
        String startDateTimeText =
                dtStartElement
                        .element("date-time")
                        .getText();

        return LocalDateTime
                .parse(startDateTimeText)
                .toLocalDate();
    }

    private static URL extractURL(Element eventElement) {
        Element urlElement =
                eventElement
                        .element("properties")
                        .element("url");

        if (urlElement == null) {
            return null;
        }

        String urlString =
                urlElement
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

    private static boolean extractIsJunior(Element eventElement) {
        String categories =
                eventElement
                        .element("properties")
                        .element("categories")
                        .element("text")
                        .getText();

        return categories.contains("Juniors Only");
    }
}
