package com.irishsea;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {
    private static File duplicate = new File("src/test/resources/analyze/case1/duplicate.txt");
    private static File aggregation = new File("src/test/resources/analyze/case1/aggregation.txt");
    private static File aggregationOutput = new File("src/test/resources/analyze/case1/aggregationReference.txt");
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeAll
    static void beforeAll() throws IOException {
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void searchDuplicatesTest() {

    }

    @Test
    void aggregateDataByCityAndFloorTest() throws IOException{
        FileAnalyzer fileAnalyzer = new FileAnalyzer(aggregation);
        fileAnalyzer.aggregateDataByCityAndFloor();
        byte[] reference = Files.readAllBytes(aggregationOutput.toPath());
        String file1 = new String(reference, StandardCharsets.UTF_8).trim();

        assertEquals(file1, outputStreamCaptor.toString().trim());

    }
}