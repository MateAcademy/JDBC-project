package com.itvdn.javastarter.test.practice_oop.entity;

/**
 * Created by Asus on 22.11.2017.
 */
public class Flower {

    private String name;
    private double price;
    private IceLevel iceLevel;
    private int heightOfSteck;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IceLevel getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(IceLevel iceLevel) {
        this.iceLevel = iceLevel;
    }

    public int getHeightOfSteck() {
        return heightOfSteck;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", iceLevel=" + iceLevel +
                ", heightOfSteck=" + heightOfSteck +
                '}';
    }

    public void setHeightOfSteck(int heightOfSteck) {
        this.heightOfSteck = heightOfSteck;
    }

    public enum IceLevel {
        NOT_GOOD(0), MEDIUM(1), NICE(2), VERY_NICE(3);

        private int id;

        IceLevel(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }

}
