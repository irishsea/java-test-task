package com.irishsea;

import com.irishsea.mergeSortVariant.FileAnalyzer;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {
    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterAll
    static void afterAll() {
        System.setOut(standardOut);
    }


    @Test
    void aggregateDataByCityAndFloorTest() throws IOException {
        int caseNumber;

        for (caseNumber = 1; caseNumber < 6; caseNumber++) {
            File aggregation = new File("src/test/resources/analyze/case" + caseNumber + "/aggregation.txt");
            File aggregationOutput = new File("src/test/resources/analyze/case" + caseNumber + "/aggregationReference.txt");
            FileAnalyzer fileAnalyzer = new FileAnalyzer(aggregation);
            fileAnalyzer.aggregateDataByCityAndFloor();

            byte[] reference = Files.readAllBytes(aggregationOutput.toPath());
            String file1 = new String(reference, StandardCharsets.UTF_8).trim();

            assertEquals(file1, outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }
    }

    @Test
    void searchDuplicatesTest() throws IOException {
        int caseNumber;

        for (caseNumber = 1; caseNumber < 5; caseNumber++) {
            File duplicate = new File("src/test/resources/analyze/case" + caseNumber + "/duplicate.txt");
            File duplicateOutput = new File("src/test/resources/analyze/case" + caseNumber + "/duplicateReference.txt");
            FileAnalyzer fileAnalyzer = new FileAnalyzer(duplicate);
            fileAnalyzer.searchDuplicates();

            byte[] reference = Files.readAllBytes(duplicateOutput.toPath());
            String file1 = new String(reference, StandardCharsets.UTF_8).trim();

            assertEquals(file1, outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }
    }
}