package com.irishsea.mergeSort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileMerge {
    public static void mergeAllFilesIntoOne() {

        int sortLevel = 0;
        int remainingFilesNumber = 9;
        int levelFilesNumber = remainingFilesNumber;

        while (remainingFilesNumber > 1) {

            File dir = new File("sorted files/" + (sortLevel + 1));
            dir.mkdirs();

            for (int i = 0; i < levelFilesNumber; i += 2) {
                try {
                    File file1 = new File("sorted files/" + sortLevel + "/temp" + i + ".txt");
                    File destFile = new File(dir + "/temp" + i / 2 + ".txt");

                    //если для индекса нет парного
                    if (i + 1 == levelFilesNumber) {
                        //файл просто переходит на следующий уровень
                        Files.copy(file1.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    } else {
                        File file2 = new File("sorted files/" + sortLevel + "/temp" + (i + 1) + ".txt");
                        mergeSortedFiles(file1, file2, destFile); //обработать случай с нечетным количеством файлов

                        remainingFilesNumber--;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            levelFilesNumber = remainingFilesNumber;
            sortLevel++;
        }

    }

    public static void mergeSortedFiles(File file1, File file2, File outputFile) throws IOException {

        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw = null;

        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            bw = new BufferedWriter(new FileWriter(outputFile));

            String line1 = br1.readLine();
            String line2 = br2.readLine();

            while (line1 != null && line2 != null) {
                if (line1.compareToIgnoreCase(line2) > 0) {
                    bw.write(line2 + "\n");
                    line2 = br2.readLine();
                } else {
                    bw.write(line1 + "\n");
                    line1 = br1.readLine();
                }
            }

            while (line1 != null) {
                bw.write(line1 + "\n");
                line1 = br1.readLine();
            }

            while (line2 != null) {
                bw.write(line2 + "\n");
                line2 = br2.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br1.close();
                br2.close();
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
