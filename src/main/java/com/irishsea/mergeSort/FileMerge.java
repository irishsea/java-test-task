package com.irishsea.mergeSort;

import com.irishsea.LineWrapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileMerge {

    public static File mergeAllFilesIntoOne(int fileAmount) {
        int sortLevel = 0;
        int remainingFilesNumber = fileAmount; //количество файлов в папке на данный момент
        // получить число после операции разбиения файла на маленькие

        int levelFilesNumber = remainingFilesNumber;

        while (remainingFilesNumber > 1) {
            String dirName = remainingFilesNumber == 2 ? "result" : String.valueOf(sortLevel + 1);
            File dir = new File("sorted files/" + dirName);
            dir.mkdirs();

            for (int i = 0; i < levelFilesNumber; i += 2) {
                try {
                    File file1 = new File("sorted files/" + sortLevel + "/temp" + i + ".txt");
                    File destFile = new File(dir + "/temp" + i / 2 + ".txt");

                    //если для индекса нет парного
                    if (i + 1 == levelFilesNumber) {
                        //файл просто переходит на следующий уровень
                        Files.copy(file1.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        break;
                    }

                    File file2 = new File("sorted files/" + sortLevel + "/temp" + (i + 1) + ".txt");
                    mergeSortedFiles(file1, file2, destFile);
                    remainingFilesNumber--;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            deleteDirectory(new File("sorted files/" + (sortLevel)));

            levelFilesNumber = remainingFilesNumber;
            sortLevel++;
        }
        return new File("sorted files/result/temp0.txt");
    }

    public static void mergeSortedFiles(File file1, File file2, File outputFile) throws IOException {

        try (
                BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line1 = br1.readLine();
            String line2 = br2.readLine();

            if (line1 != null && line2 != null) {
                LineWrapper lineWrapper1 = new LineWrapper(line1);
                LineWrapper lineWrapper2 = new LineWrapper(line2);

                while (true) {
                    if (lineWrapper2.compareTo(lineWrapper1) > 0) { //если вторая строка "меньше"
                        bw.write(line1 + "\n");
                        line1 = br1.readLine();

                        if (line1 == null) {
                            break;
                        }

                        lineWrapper1 = new LineWrapper(line1);
                    } else {
                        bw.write(line2 + "\n");
                        line2 = br2.readLine();

                        if (line2 == null) {
                            break;
                        }

                        lineWrapper2 = new LineWrapper(line2);
                    }
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
        }

    }

    private static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
