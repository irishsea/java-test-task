package com.irishsea.LineParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        File file1 = new File("test1.txt");
        BufferedReader reader = null;
        try {
            ArrayList<String> linesList = new ArrayList<>();
            reader = new BufferedReader(new FileReader(file1));
            String line;

            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }

            Collections.sort(linesList);

            /**
             * метод для записи в файл
             */

            File file = new File("tmpFiles/test3.txt");
            FileWriter fr = null;
            BufferedWriter br = null;

            try {

                fr = new FileWriter(file);
                br = new BufferedWriter(fr);

                for (int i = 0; i < linesList.size(); i++) {
                    br.write(linesList.get(i) + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.flush();
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**
             * Конец метода записи в файл
             */

            for (String string : linesList) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
