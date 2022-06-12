package com.irishsea.iterators;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CsvIterator implements Iterator<String> {
    private String cachedLine;
    private final BufferedReader reader;

    private boolean finished = false;

    public CsvIterator(final File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        /** пропускаем шапку таблицы CSV */
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
            String line = reader.readLine();

            if (line == null) {
                finished = true;
                return false;
            }
            cachedLine = line;
            return true;

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
