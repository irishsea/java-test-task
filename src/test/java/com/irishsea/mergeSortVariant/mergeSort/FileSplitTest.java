package com.irishsea.mergeSortVariant.mergeSort;

import com.irishsea.mergeSortVariant.iterators.CsvIterator;
import com.irishsea.mergeSortVariant.iterators.XmlIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class FileSplitTest {
    private final static String destPath = "src/test/resources/split/case";

    @BeforeEach
    void setUp() {
    }

    @Test
    void splitLargeFileIntoSmallFiles() throws IOException, XMLStreamException {
        int caseNumber;

        for (caseNumber = 1; caseNumber < 4; caseNumber++) {
            File csvPath = new File(destPath + caseNumber + "/csv/");
            File xmlPath = new File(destPath + caseNumber + "/xml/");
            File refPath = new File(destPath + caseNumber + "/reference/");
            csvPath.mkdirs();
            xmlPath.mkdirs();

            Iterator<String> iteratorCsv =
                    new CsvIterator(new File(destPath + caseNumber + "/inputCSV.csv"));
            Iterator<String> iteratorXml =
                    new XmlIterator(new File(destPath + caseNumber + "/inputXML.xml"));

            int fileAmountC =
                    new FileSplit(iteratorCsv).splitLargeFileIntoSmallFiles(4, csvPath.getPath());
            int fileAmountX =
                    new FileSplit(iteratorXml).splitLargeFileIntoSmallFiles(4, xmlPath.getPath());

            File[] listRef = new File(xmlPath.getPath()).listFiles();

            for (int i = 0; i < listRef.length; i++) {
                byte[] arCsv = Files.readAllBytes(new File(csvPath + "/temp" + i + ".txt").toPath());
                byte[] arXml = Files.readAllBytes(new File(xmlPath + "/temp" + i + ".txt").toPath());
                byte[] arRef = Files.readAllBytes(new File(refPath + "/temp" + i + ".txt").toPath());
                String outputCsv = new String(arCsv, StandardCharsets.UTF_8).trim();
                String outputXml = new String(arXml, StandardCharsets.UTF_8).trim();
                String reference = new String(arRef, StandardCharsets.UTF_8).trim();

                assertEquals(reference, outputCsv);
                assertEquals(reference, outputXml);
            }
            FileMerge.deleteDirectory(csvPath);
            FileMerge.deleteDirectory(xmlPath);
        }
    }
}