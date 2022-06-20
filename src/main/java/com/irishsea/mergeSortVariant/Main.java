package com.irishsea.mergeSortVariant;

import com.irishsea.mergeSortVariant.iterators.CsvIterator;
import com.irishsea.mergeSortVariant.iterators.XmlIterator;
import com.irishsea.mergeSortVariant.mergeSort.FileMerge;
import com.irishsea.mergeSortVariant.mergeSort.FileSplit;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) throws XMLStreamException, IOException {
        String exit = "exit";

        while (true) {
            System.out.println("Введите путь до файла или \"exit\", чтобы выйти из программы.");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals(exit)) {
                System.out.println("Вы завершили работу программы.");
                break;
            }
            File sourceFile = new File(line);

            if (!sourceFile.exists()) {
                System.out.println("Указан неверный путь, файл не существует.");
                continue;
            }

            Iterator<String> iterator;
            if (getFileExtension(sourceFile).equals("csv")) {
                iterator = new CsvIterator(sourceFile);
            } else if (getFileExtension(sourceFile).equals("xml")) {
                iterator = new XmlIterator(sourceFile);
            } else {
                System.out.println("Некорректное расширение файла, используйте файлы с расширениями CSV или XML.");
                continue;
            }
            try {
                System.out.println("Старт анализа файла.");
                long startSplit = System.currentTimeMillis();
                int fileAmount = new FileSplit(iterator).splitLargeFileIntoSmallFiles(400000, "sorted files/0");
                long endSplit = System.currentTimeMillis();
                System.out.println("Время разделения на отсортированные файлы: " + (endSplit - startSplit));

                long startMerge = System.currentTimeMillis();
                File destFile = FileMerge.mergeAllFilesIntoOne(fileAmount);
                long endMerge = System.currentTimeMillis();
                System.out.println("Время слияния отсортированных файлов: " + (endMerge - startMerge));

                long startAnalyze = System.currentTimeMillis();
                FileAnalyzer fileAnalyzer = new FileAnalyzer(destFile);
                fileAnalyzer.searchDuplicates();
                fileAnalyzer.aggregateDataByCityAndFloor();
                long endAnalyze = System.currentTimeMillis();
                System.out.println("Время формирования статистики из отсортированного файла: " + (endAnalyze - startAnalyze));

                FileMerge.deleteDirectory(new File("sorted files"));

            } catch (IOException | XMLStreamException e) {
                e.printStackTrace();
            }
        }
        in.close();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка, и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        // в противном случае возвращаем заглушку, то есть расширение не найдено
        return "";
    }
}
