/**
 * Класс - обертка над строкой, дает доступ к частям строки
 */
package com.irishsea;


public class LineWrapper implements Comparable<LineWrapper> {

    public final String line;
    public final String city;
    public final String street;
    public final String house;
    public final int floor;

    public LineWrapper(String line) {
        this.line = line;
        // операция split добавляет около 1.5-2 секунд для всех записей;
        // обслуживание поля attributes[] - еще около 3 секунд.
        String[] attributes = line.split(";");
        this.city = attributes[0];
        this.street = attributes[1];
        this.house = attributes[2];
        this.floor = Integer.parseInt(attributes[3]);
    }

    @Override
    public int compareTo(LineWrapper o) {
        /**
         * сравнение двух строк по всем атрибутам (город, этажи, улица, номер дома)
         */

//        return o.line.compareTo(line);

        int result = o.city.compareToIgnoreCase(this.city);

        if (result != 0) {
            return result;
        }

        result = Integer.compare(o.floor, this.floor);

        if (result != 0) {
            return result;
        }

        result = o.street.compareToIgnoreCase(this.street);

        if (result != 0) {
            return result;
        }

        result = o.house.compareToIgnoreCase(this.house);

        return result;
    }
}
