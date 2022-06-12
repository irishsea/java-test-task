package com.irishsea.mergeSort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


class FileMergeTest {
    private static FileMerge fileMerge;

    private static File file1 = new File("src/test/resources/case1/input1.txt");
    private static File file2 = new File("src/test/resources/case1/input2.txt");
    private static File outputFile = new File("src/test/resources/case1/output.txt");
    private static File referenceFile = new File("src/test/resources/case1/reference.txt");

    @BeforeAll
    static void beforeAll() throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void mergeAllFilesIntoOneTest() {
    }

    @Test
    void mergeSortedFilesTest() throws IOException{
        FileMerge.mergeSortedFiles(file1, file2, outputFile);
        byte[] output = Files.readAllBytes(outputFile.toPath());
        byte[] reference = Files.readAllBytes(referenceFile.toPath());
        String file1 = new String(output, StandardCharsets.UTF_8).trim();
        String file2 = new String(reference, StandardCharsets.UTF_8).trim();

        assertEquals(file2, file1);

    }
}