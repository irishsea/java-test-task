package com.irishsea.mergeSort;

import com.irishsea.LineWrapper;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FileSplit {
    private final Iterator<String> iterator;

    /**
     * Метод, который принимает исходный большой файл
     */

    public FileSplit(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    public int splitLargeFileIntoSmallFiles(int linesPerFile) throws XMLStreamException {
        int fileCounter = 0;
        File outputDirectory = new File("sorted files/0");
        outputDirectory.mkdirs();

        ArrayList<LineWrapper> linesList = new ArrayList<>();
        String line;

        while (iterator.hasNext()) {  //что если в файле встретится просто пустая строка?
            line = iterator.next();

            /**
             * обертка над строкой LineWrapper позволяет отсортировать в их порядке "город > кол-во этажей > улица > дом
             * для более легкой операции агрегации в дальнейшем
             **/
            LineWrapper lineWrapper = new LineWrapper(line);
            linesList.add(lineWrapper);

            //как только набралось нужное количество строк для записи в файл
            if (linesList.size() == linesPerFile || !iterator.hasNext()) {
                Collections.sort(linesList); //сортировка строк в файле

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory
                        + "/temp"
                        + fileCounter
                        + ".txt"))) {
                    for (LineWrapper row : linesList) {
                        writer.write(row.line + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fileCounter++;
                linesList.clear();
            }
        }

        return fileCounter;
    }
}
