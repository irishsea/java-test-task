package com.irishsea.hashMapVariant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class CsvIterator implements Iterator<LineWrapper> {
    private LineWrapper cashed;
    private final BufferedReader reader;

    private boolean finished = false;

    public CsvIterator(final File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        /* пропускаем шапку таблицы CSV
         * todo: учесть, что файл может содержать лишь одну строку без шапки */
        reader.readLine();
    }

    @Override
    public boolean hasNext() {
        if (cashed != null) {
            return true;
        }

        if (finished) { //если строки в документе закончились
            return false;
        }

        try {
            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    finished = true;
                    return false;
                }

                if (line.equals("")) { //проверка на пустую строку
                    continue;
                }
                cashed = new LineWrapper(line);
                return true;
            }

        } catch (IOException ioe) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new IllegalStateException(ioe.toString());
        }
    }

    @Override
    public LineWrapper next() {
        if (!hasNext()) {
            return null;
        }

        LineWrapper current = cashed;
        cashed = null;
        return current;
    }
}
