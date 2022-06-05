/**
 * Класс - обертка над строкой, дает доступ к частям строки
 */
package com.irishsea.LineParser;

import java.util.regex.Pattern;


public class LineWrapper implements Comparable<LineWrapper> {

    public final String line;
    private String[] attributes;

    public LineWrapper(String line) {
        this.line = line;
        // операция split добавляет около 1.5-2 секунд для всех записей;
        // обслуживание поля attributes - еще около 3 секунд.
        // Навероное, стоит вернуть 4 стринговых поля.
        parseLine();
    }

    public String getCity() {
        return attributes[0];
    }

    public String getStreet() {
        return attributes[1];
    }

    public String getHouse() {
        return attributes[2];
    }

    public int getFloor() {
        return Integer.parseInt(attributes[3]);
    }

//    public String getModifiedLine() {
//        return getCity() + ";" + getFloor() + ";" + getStreet() + ";" + getHouse();
//    }

    public void parseLine() {
        /**
         * Получать данные по индексу массива опасно, потому что могут поступить данные с другим порядком
         */

        this.attributes = line.split(";");
    }


    @Override
    public int compareTo(LineWrapper o) {
        /**
         * сравнение двух строк по всем атрибутам (город, этажи, улица, номер дома)
         * функция на данном этапе разработки нигде не используется
         */

        return o.line.compareTo(line);

//        int result = o.getCity().compareTo(getCity());
//
//        if (result != 0) {
//            return result;
//        }
//
//        result = Integer.compare(o.getFloor(), getFloor());
//
//        if (result != 0) {
//            return result;
//        }
//
//        result = o.getStreet().compareToIgnoreCase(getStreet());
//
//        if (result != 0) {
//            return result;
//        }
//
//        result = o.getHouse().compareTo(getHouse());
//
//        if (result != 0) {
//            return result;
//        }
//
//        return 0;
    }


}
