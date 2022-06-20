package com.irishsea.mergeSortVariant.mergeSort;

import com.irishsea.mergeSortVariant.LineWrapper;
import com.irishsea.mergeSortVariant.iterators.CommonIterator;

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
        CommonIterator iterator1 = new CommonIterator(file1);
        CommonIterator iterator2 = new CommonIterator(file2);
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            LineWrapper item1 = iterator1.next();
            LineWrapper item2 = iterator2.next();

            while (item1 != null && item2 != null) {
                if (item1.compareTo(item2) > 0) { //если первая строка "больше"
                    bw.write(item1.line + "\n");
                    item1 = iterator1.next();
                } else {
                    bw.write(item2.line + "\n");
                    item2 = iterator2.next();
                }
            }

            while (item1 != null) {
                bw.write(item1.line + "\n");
                item1 = iterator1.next();
            }

            while (item2 != null) {
                bw.write(item2.line + "\n");
                item2 = iterator2.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }
}
