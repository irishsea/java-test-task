package com.irishsea;

import com.irishsea.iterators.CsvIterator;
import com.irishsea.iterators.XmlIterator;
import com.irishsea.mergeSort.FileAnalyzer;
import com.irishsea.mergeSort.FileMerge;
import com.irishsea.mergeSort.FileSplit;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException{

        /**
         * проверка разбиения большого файла на маленькие по 400.000 строк в каждом
         */

        File sourceFileCsv = new File("sourceLargeFile.csv");
//        File sourceFile = new File("testSourceFile.csv");
        File sourceFileXml = new File("address.xml");
//        File sourceFile = new File("addressLite.xml");

        /**
         * Проверка, как ищутся дубликаты и аггрегируются данные
         */
        Iterator<String> iterator;
        if (getFileExtension(sourceFileXml).equals("csv")) {
            iterator = new CsvIterator(sourceFileCsv);
        } else {
            iterator = new XmlIterator(sourceFileXml);
        }
        try {
            long startSplit = System.currentTimeMillis();
            int fileAmount = new FileSplit(iterator).splitLargeFileIntoSmallFiles(400000);
            long endSplit = System.currentTimeMillis();
            System.out.println("Время разделения файлов и сортировки: " + (endSplit - startSplit));

            long startMerge = System.currentTimeMillis();
            File destFile = FileMerge.mergeAllFilesIntoOne(fileAmount);
            long endMerge = System.currentTimeMillis();
            System.out.println("Время слияния файлов: " + (endMerge - startMerge));

            long startAnalyze = System.currentTimeMillis();
            new FileAnalyzer(destFile);
            long endAnalyze = System.currentTimeMillis();
            System.out.println("Время анализа файлов: " + (endAnalyze - startAnalyze));

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }


        /**
         * Проверка, как парсится одна строка
         */

//        String lineForParse = new String("\"Батайск\";\"Мостотреста, улица\";133;4");
//        LineWrapper lineWrapper = new LineWrapper(lineForParse);


        /**
         * Проверка, как программа работает с xml файлом
         */

//        try {
//            StaxProcessor staxProcessor = new StaxProcessor();
//            File fromXmlFile = staxProcessor.parseFile(sourceFile);
//
//            int fileAmount = FileSplit.splitLargeFileIntoSmallFiles(fromXmlFile);
//            File destFile = FileMerge.mergeAllFilesIntoOne(fileAmount);
//            FileAnalyzer fileAnalyzer = new FileAnalyzer(destFile);
//        } catch (IOException | XMLStreamException e) {
//            e.printStackTrace();
//        }

    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка, и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
