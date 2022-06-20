package com.irishsea.mergeSortVariant.iterators;

import java.io.*;
import java.util.Iterator;


public class CsvIterator implements Iterator<String> {
    private String cachedLine;
    private final BufferedReader reader;

    private boolean finished = false;

    public CsvIterator(final File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        /* пропускаем шапку таблицы CSV */
        reader.readLine();
    }

    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
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
                cachedLine = line;
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
    public String next() {
        if (!hasNext()) {
            return null;
        }

        String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

    private boolean isValid() {
        return false;
    }
}
