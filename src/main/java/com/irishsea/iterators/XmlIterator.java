package com.irishsea.iterators;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XmlIterator implements Iterator<String> {
    private String cachedLine;
    private final XMLStreamReader reader;

    public XmlIterator(final File file) throws FileNotFoundException, XMLStreamException {
        final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        this.reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(file));
    }

    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        }

        try {
            while (reader.hasNext()) {
                int eventType = reader.next();

                boolean isValidEvent = eventType == XMLEvent.START_ELEMENT
                        && (reader.getName().getLocalPart().equals("item"));

                if (isValidEvent) {
                    String city = reader.getAttributeValue(null, "city");
                    String street = reader.getAttributeValue(null, "street");
                    String house = reader.getAttributeValue(null, "house");
                    int floor = Integer.parseInt(reader.getAttributeValue(null, "floor"));

                    cachedLine = city + ";"
                            + street + ";"
                            + house + ";"
                            + floor;

                    return true;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("File is finished or it has empty lines");
        }

        String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }
}
