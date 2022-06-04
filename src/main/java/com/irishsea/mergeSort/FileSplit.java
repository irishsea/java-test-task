package com.irishsea.mergeSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FileSplit {
    /**
     * Метод, который принимает исходный большой файл
     */

    public static int splitLargeFileIntoSmallFiles(File sourceFile) {
//        final int amountOfResultFiles = 4;
        final int linesPerFile = 400000;
        int fileCounter = 0;
        File outputDirectory = new File("sorted files/0");
        outputDirectory.mkdirs();
        BufferedReader reader = null;


        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            ArrayList<String> linesList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {  //что если в файле встретится просто пустая строка?
                /**
                 * тут изменить строку, чтобы количество этажей было на втором месте
                 */

                linesList.add(line);

                //как только набралось нужное количество строк для записи в файл
                if (linesList.size() == linesPerFile) {
                    Collections.sort(linesList); //сортировка строк в файле

                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter(outputDirectory
                                + "/temp"
                                + fileCounter
                                + ".txt"));

                        for (String row : linesList) {
                            writer.write(row + "\n");
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

//    public static String modifyLine(String line){
//
//    }
}
