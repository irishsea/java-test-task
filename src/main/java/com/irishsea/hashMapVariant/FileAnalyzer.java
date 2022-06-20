package com.irishsea.hashMapVariant;

import com.irishsea.mergeSortVariant.iterators.CommonIterator;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FileAnalyzer {
    private final HashMap<LineWrapper, Integer> hashMap;
    private final Iterator<LineWrapper> iterator;

    public FileAnalyzer(File file, Iterator<LineWrapper> iterator) throws IOException {
        this.iterator = iterator;
        hashMap = new HashMap<>();
    }

    public void searchDuplicates() {
        LineWrapper item;

        while (iterator.hasNext()) {
            item = iterator.next();

            if (hashMap.containsKey(item)) {
                int currentValue = hashMap.get(item);
                hashMap.replace(item, currentValue, currentValue + 1);
            } else {
                hashMap.put(item, 1);
            }
        }
        for (LineWrapper lw : hashMap.keySet()) {
            if (hashMap.get(lw) > 1) {
                System.out.println(lw + ", количество дубликатов: " + hashMap.get(lw));
            }
        }
    }

    public void aggregateDataByCityAndFloor() throws FileNotFoundException {
        /*todo: реализовать*/
    }
}
