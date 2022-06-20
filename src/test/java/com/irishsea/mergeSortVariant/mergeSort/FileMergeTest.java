package com.irishsea.mergeSortVariant.mergeSort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


class FileMergeTest {

    @Test
    void mergeFiles() throws IOException {
        int caseNumber;

        for (caseNumber = 1; caseNumber < 5; caseNumber++) {
            File file1 = new File("src/test/resources/merge/case" + caseNumber + "/input1.txt");
            File file2 = new File("src/test/resources/merge/case" + caseNumber + "/input2.txt");
            File outputFile = new File("src/test/resources/merge/case" + caseNumber + "/output.txt");
            File referenceFile = new File("src/test/resources/merge/case" + caseNumber + "/reference.txt");

            FileMerge.mergeSortedFiles(file1, file2, outputFile);
            byte[] output = Files.readAllBytes(outputFile.toPath());
            byte[] reference = Files.readAllBytes(referenceFile.toPath());
            String outputString = new String(output, StandardCharsets.UTF_8).trim();
            String referenceString = new String(reference, StandardCharsets.UTF_8).trim();

            assertEquals(referenceString, outputString);

            PrintWriter writer = new PrintWriter(outputFile);
            writer.print("");
            writer.close();
        }
    }

}