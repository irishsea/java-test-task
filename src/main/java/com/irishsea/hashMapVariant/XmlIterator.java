package com.irishsea.hashMapVariant;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class XmlIterator implements Iterator<LineWrapper> {
    private LineWrapper cachedLW;
    private final XMLStreamReader reader;

    /*закрывать reader, когда класс отработает*/
    public XmlIterator(final File file) throws FileNotFoundException, XMLStreamException {
        final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        this.reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(file));
    }

    @Override
    public boolean hasNext() {
        if (cachedLW != null) {
            return true;
        }

        try {
            while (reader.hasNext()) {
                int eventType = reader.next();

                boolean isValidEvent = eventType == XMLEvent.START_ELEMENT
                        && (reader.getName().getLocalPart().equals("item"));

                if (isValidEvent) {
                     cachedLW = new LineWrapper
                            (reader.getAttributeValue(null, "city"),
                             reader.getAttributeValue(null, "street"),
                             reader.getAttributeValue(null, "house"),
                             Integer.parseInt(reader.getAttributeValue(null, "floor")));
                    return true;
                }
            }
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LineWrapper next() {
        if (!hasNext()) {
            return null;
        }

        LineWrapper currentLW = cachedLW;
        cachedLW = null;
        return currentLW;
    }
}
