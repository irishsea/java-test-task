package com.irishsea;

import com.irishsea.LineWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzer {
    private final File file;

    public FileAnalyzer(File file) throws IOException {
        this.file = file;
        searchDuplicates();
        aggregateDataByCityAndFloor();
    }

    public void searchDuplicates() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String previousLine;
            int duplicateCounter = 0;

            String line = reader.readLine();

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

    public void aggregateDataByCityAndFloor() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            LineWrapper current = null;
            LineWrapper previous;
            int recordCounter = 1;

            while ((line = reader.readLine()) != null) { //основной цикл чтения файла
                previous = current;
                current = new LineWrapper(line);

                if (previous == null) {
                    continue;
                }

                boolean isEqual = previous.city.equals(current.city)
                        && previous.floor == current.floor;

                if (isEqual) {
                    recordCounter++;
                } else {
                    /**
                     * как только началась новая группа данных,
                     * выводим данные о предыдущей группе
                     */
                    System.out.println("Количество домов в городе: "
                            + previous.city
                            + " с количеством этажей: "
                            + previous.floor
                            + " равно: "
                            + recordCounter);

                    recordCounter = 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
