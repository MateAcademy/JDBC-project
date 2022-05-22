package com.itvdn.javastarter.test.practice_oop.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 22.11.2017.
 */
public class Bunch {

    private List<Flower> flowers;
    private double price;

    public Bunch() {
        flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
        price += flower.getPrice();
    }

    public double price() {
        return price;
    }

    @Override
    public String toString() {
        return flowers.toString();
    }

    public List<Flower> getFlowersByStables(int begin, int end){
        List<Flower> result = new ArrayList<>();

        for (Flower flower : flowers) {
            int heightSteack = flower.getHeightOfSteck();
            if (heightSteack >= begin && heightSteack <= end) {
                result.add(flower);
            }
        }

        return result;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

}
