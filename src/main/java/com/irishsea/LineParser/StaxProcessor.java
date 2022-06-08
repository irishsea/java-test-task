package com.irishsea.LineParser;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaxProcessor {

    private String city;
    private String street;
    private String house;
    private int floor;


    public File parseFile(File file) throws XMLStreamException, IOException {
        return printXmlByXmlCursorReader(file);

    }

    private File printXmlByXmlCursorReader(File file) //поменять название функции
            throws XMLStreamException, IOException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(file));

        File outputFile = new File("sorted files/xmltest.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));


        int eventType;

        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                if (reader.getName().getLocalPart().equals("item")) {

                    /**
                     * проверка, как парсится документ в принципе
                     */
                    city = reader.getAttributeValue(null, "city");
                    street = reader.getAttributeValue(null, "street");
                    house = reader.getAttributeValue(null, "house");
                    floor = Integer.parseInt(reader.getAttributeValue(null, "floor"));
                    String result = city + ";"
                            + street + ";"
                            + house + ";"
                            + floor + ";" + "\n";

                    writer.write(result);
                }

            }

//            if (eventType == XMLEvent.END_ELEMENT) {
//                // if </item>
//                if (reader.getName().getLocalPart().equals("staff")) {
//                    System.out.printf("%n%s%n%n", "---");
//                }
//            }

        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }

}
