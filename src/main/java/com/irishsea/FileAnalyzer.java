package com.irishsea;

import com.irishsea.iterators.CommonIterator;

import java.io.*;

public class FileAnalyzer {
    private final File file;

    public FileAnalyzer(File file) throws IOException {
        this.file = file;
//        searchDuplicates();
//        aggregateDataByCityAndFloor();
    }

    public void searchDuplicates() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int duplicateCounter = 0;
            String line = reader.readLine();
            String previousLine;

            while (line != null) { //основной цикл чтения файла
                previousLine = line;
                line = reader.readLine();

                if (previousLine.equals(line)) {
                    duplicateCounter++;

                } else if (duplicateCounter > 0) {
                    System.out.println(previousLine + " количество дубликатов: " + duplicateCounter);
                    duplicateCounter = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggregateDataByCityAndFloor() throws FileNotFoundException {
        CommonIterator iterator = new CommonIterator(file);

        int recordCounter = 1;
        LineWrapper current = iterator.next();

        while (current != null) {
            LineWrapper previous = current;
            current = iterator.next();

            // Считаем количество эквивалентных элементов, идущих подряд
            while (current != null) {
                boolean isEqual = previous.city.equals(current.city)
                        && previous.floor == current.floor;

                if (isEqual) {
                    recordCounter++;
                    previous = current;
                    current = iterator.next();
                } else {
                    break;
                }
            }

            System.out.println("Количество домов в городе: "
                    + previous.city
                    + " с количеством этажей: "
                    + previous.floor
                    + " равно: "
                    + recordCounter);

            recordCounter = 1;
        }
    }
}
