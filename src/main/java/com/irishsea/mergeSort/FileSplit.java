package com.irishsea.mergeSort;

import com.irishsea.LineParser.LineWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FileSplit {
    /**
     * Метод, который принимает исходный большой файл
     */

    public static int splitLargeFileIntoSmallFiles(File sourceFile) {

        final int linesPerFile = 400000;
        int fileCounter = 0;
        File outputDirectory = new File("sorted files/0");
        outputDirectory.mkdirs();
        BufferedReader reader = null;


        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            ArrayList<LineWrapper> linesList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {  //что если в файле встретится просто пустая строка?
                /**
                 * обертка над строкой позволяет отсортировать в их порядке "город > кол-во этажей > улица > дом
                 * для более легкой операции агрегации в дальнейшем
                 **/
                LineWrapper lineWrapper = new LineWrapper(line);

                linesList.add(lineWrapper);

                //как только набралось нужное количество строк для записи в файл
                if (linesList.size() == linesPerFile) {
                    Collections.sort(linesList); //сортировка строк в файле

                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter(outputDirectory
                                + "/temp"
                                + fileCounter
                                + ".txt"));

                        for (LineWrapper row : linesList) {
                            writer.write(row.line + "\n");
                        }

                    } finally {
                        writer.flush();
                        writer.close();
                    }

                    fileCounter++;
                    linesList.clear();

                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileCounter;
    }

    public static void testSpeedOfReadingFile(File sourceFile) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        String line;

        while ((line = reader.readLine()) != null) {

        }
        long end = System.currentTimeMillis();
        System.out.println("Время чтения файла: " + (end - start));
    }

    public static String modifyLine(String line) {
        return null;
    }
}
