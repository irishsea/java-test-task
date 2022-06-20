package com.irishsea.mergeSortVariant.iterators;

import com.irishsea.mergeSortVariant.LineWrapper;

import java.io.*;
import java.util.Iterator;

public class CommonIterator implements Iterator<LineWrapper> {
    private LineWrapper cachedItem;
    private final BufferedReader reader;
    private boolean finished = false;

    public CommonIterator(final File file) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public boolean hasNext() {
        if (cachedItem != null) {
            return true;
        }

        if (finished) { //если строки в документе закончились
            return false;
        }

        try {
            String line = reader.readLine();

            if (line == null) {
                finished = true;
                reader.close();
                return false;
            }

            cachedItem = new LineWrapper(line);
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
    public LineWrapper next() {
        if (!hasNext()) {
            return null;
        }

        LineWrapper currentItem = cachedItem;
        cachedItem = null;
        return currentItem;
    }
}
