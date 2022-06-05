package com.irishsea.LineParser;

import com.irishsea.mergeSort.FileMerge;
import com.irishsea.mergeSort.FileSplit;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
//        File file1 = new File("test2.txt");
//        BufferedReader reader = null;
//        try {
//            ArrayList<String> linesList = new ArrayList<>();
//            reader = new BufferedReader(new FileReader(file1));
//
//            String line = reader.readLine();
//            while (line  != null) {
//                linesList.add(line);
//                line = reader.readLine();
//            }
//
//            Collections.sort(linesList);
//
//            /**
//             * метод для записи в файл
//             */
//
//            File file = new File("tmpFiles/test4.txt");
////            File file2 = new File("tmpFiles/test4.txt");
//            FileWriter fr = null;
//            BufferedWriter br = null;
//
//            try {
//
//                fr = new FileWriter(file);
//                br = new BufferedWriter(fr);
//
//                for (String s : linesList) {
//                    br.write(s + "\n");
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    br.flush();
//                    br.close();
//                    fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            /**
//             * Конец метода записи в файл
//             */
//
//            for (String string : linesList) {
//                System.out.println(string);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * проверка слияния двух остортированных файлов
         */

//        File sortedFile1 = new File("tmpFiles/test3.txt");
//        File sortedFile2 = new File("tmpFiles/test4.txt");
//        FileMerge fileMerge = new FileMerge();
//        try {
//            fileMerge.mergeSortedFiles(sortedFile1, sortedFile2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /**
         * проверка слияния 8 файликов в 4
         */

//        FileMerge.mergeAllFilesIntoOne();

        /**
         * проверка разбиения большого файла на маленькие по 400.000 строк в каждом
         */

        File sourceFile = new File("sourceLargeFile.csv");

        //от 5 секунд до 15 в зависимости от загруженности диска
        long start1 = System.currentTimeMillis();
        int fileAmount = FileSplit.splitLargeFileIntoSmallFiles(sourceFile);
        long end1 = System.currentTimeMillis();
        System.out.println("Общее время разделения файла: " + (end1 - start1));

        //от 11 секунд до 20 в зависимости от загруженности диска
        long start2 = System.currentTimeMillis();
        FileMerge.mergeAllFilesIntoOne(fileAmount);
        long end2 = System.currentTimeMillis();
        System.out.println("Общее время слияния файлов: " + (end2 - start2));

//        try {
//            FileSplit.testSpeedOfReadingFile(sourceFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /**
         * Проверка, как парсится одна строка
         */

//        String lineForParse = new String("\"Батайск\";\"Мостотреста, улица\";133;4");
//        LineWrapper lineWrapper = new LineWrapper(lineForParse);


    }
}
