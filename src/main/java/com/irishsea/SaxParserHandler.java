package com.irishsea;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SaxParserHandler extends DefaultHandler {
    private final String ITEM_TAG = "item";
    private HashMap<String, Integer> parserMap;
    StringBuilder stringBuilder = new StringBuilder();

    public SaxParserHandler(HashMap<String, Integer> parserMap) {
        this.parserMap = parserMap;
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("Start the document");
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("End the document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ITEM_TAG)) {

            for (int i = 0; i < attributes.getLength(); i++) {
                stringBuilder.append(attributes.getValue(i));
            }
//            System.out.println(stringBuilder.toString());
            if (parserMap.containsKey(stringBuilder.toString())) {
                int currentValue = parserMap.get(stringBuilder.toString());
                parserMap.replace(stringBuilder.toString(), currentValue, currentValue + 1);
            } else {
                parserMap.put(stringBuilder.toString(), 1);
            }
            stringBuilder.setLength(0);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }


}
