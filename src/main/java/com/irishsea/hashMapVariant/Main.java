package com.irishsea.hashMapVariant;

import com.irishsea.hashMapVariant.CsvIterator;
import com.irishsea.hashMapVariant.XmlIterator;


import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

            Iterator<LineWrapper> iterator;
            if (getFileExtension(sourceFile).equals("csv")) {
                iterator = new CsvIterator(sourceFile);
            } else if (getFileExtension(sourceFile).equals("xml")) {
                iterator = new XmlIterator(sourceFile);
            } else {
                System.out.println("Некорректное расширение файла, используйте файлы с расширениями CSV или XML.");
                continue;
            }
            System.out.println("Старт анализа файла.");
            System.out.println("Поиск дубликатов.");
            FileAnalyzer fileAnalyzer = new FileAnalyzer(sourceFile, iterator);
            fileAnalyzer.searchDuplicates();
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
