package com.irishsea;

import com.irishsea.LineWrapper;
import com.irishsea.iterators.CommonIterator;

import javax.sound.sampled.Line;
import java.io.*;

public class FileAnalyzer {
    private final File file;

    public FileAnalyzer(File file) throws IOException {
        this.file = file;
        searchDuplicates();
        aggregateDataByCityAndFloor();
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

        // Если файл пуст
        if (!iterator.hasNext()) {
            return;
        }

        int recordCounter = 1;
        LineWrapper current = iterator.next();

        // Если в файле 1 запись
        if (!iterator.hasNext()) {
            System.out.println("Количество домов в городе: "
                    + current.city
                    + " с количеством этажей: "
                    + current.floor
                    + " равно: "
                    + recordCounter);
            return;
        }

        while (iterator.hasNext()) {
            LineWrapper previous = current;
            current = iterator.next();

            boolean isEqual = previous.city.equals(current.city)
                    && previous.floor == current.floor;

            if (isEqual) {
                recordCounter++;

                // Если текущая запись последняя
                if (!iterator.hasNext()) {
                    System.out.println("Количество домов в городе: "
                            + current.city
                            + " с количеством этажей: "
                            + current.floor
                            + " равно: "
                            + recordCounter);
                }

                continue;
            }

            // Если обнаружили начало новой группы
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
