/**
 * Класс - обертка над строкой, дает доступ к частям строки
 */
package com.irishsea.hashMapVariant;


import java.util.Objects;

public class LineWrapper {

    private String line;
    private String city;
    private String street;
    private String house;
    private int floor;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getLine() {
        return line = getCity() + ";" + getStreet() + ";" + getHouse() + ";" + getFloor();
    }

    public LineWrapper(String line) {
        this.line = line;
        String[] attributes = line.split(";");
        this.city = attributes[0];
        this.street = attributes[1];
        this.house = attributes[2];
        this.floor = Integer.parseInt(attributes[3]);
    }

    public LineWrapper(String city, String street, String house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
        getLine();
    }

    /* методы для вставки в HashMap */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineWrapper)) return false;
        LineWrapper that = (LineWrapper) o;
        return floor == that.floor && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(house, that.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    /*todo: придумать, как использовать equals() и hashCode() для агрегации данных */
    public boolean equalsByCityAndFloor(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineWrapper)) return false;
        LineWrapper that = (LineWrapper) o;
        return floor == that.floor && Objects.equals(city, that.city);
    }

    public int hashCodeByCityAndFloor() {
        return Objects.hash(city, floor);
    }

    @Override
    public String toString() {
        return "city = " + city +
                ", street = " + street +
                ", house = " + house +
                ", floor = " + floor;
    }
}
