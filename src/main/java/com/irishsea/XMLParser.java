package com.irishsea;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLParser {
    private File file;

    public XMLParser(File file){
        this.file = file;
    }
    public void parse(HashMap<String, Integer> parserMap){
        try {
            SAXParserFactory spFactory = SAXParserFactory.newInstance();
            SaxParserHandler spHandler = new SaxParserHandler(parserMap);
            SAXParser parser = spFactory.newSAXParser();
            parser.parse(file, spHandler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
