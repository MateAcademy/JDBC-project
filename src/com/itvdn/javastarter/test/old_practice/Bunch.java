package com.itvdn.javastarter.test.old_practice;

public class Bunch {

    static String[][] flowers = {
            {"Rose", "$20", "5", "10"},
            {"Tulpan", "$15", "4", "8"},
            {"Romashka", "$10", "3", "9"}
    };

    static final int NAME = 0;
    static final int PRICE = 1;
    static final int ICE = 2;
    static final int STEACK = 3;


    static int price() {
        int sum = 0;


        for (int i = 0; i < flowers.length; i++) {
            int price = Integer.parseInt(flowers[i][PRICE].substring(1));
            sum += price;
        }



        return sum;
    }

    static void sort() {
        for (int j = 0; j < flowers.length; j++) {
            for (int i = 0; i < flowers.length-1; i++) {
                String[] currentFlower = flowers[i];
                String[] nextFlower = flowers[i+1];
                int iceCurrent = Integer.parseInt(currentFlower[ICE]);
                int iceNext = Integer.parseInt(nextFlower[ICE]);

                if (iceCurrent > iceNext) {
                    flowers[i] = nextFlower;
                    flowers[i+1] = currentFlower;
                }

            }
        }

    }

    static void printFlowers() {
        for (int i = 0; i < flowers.length; i++) {
            for (int j = 0; j < flowers[i].length; j++) {
                System.out.print(flowers[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void searchForSteack(int from, int to) {
        if (from <= to) {
            for (int i = 0; i < flowers.length; i++) {
                int steackLength = Integer.parseInt(flowers[i][STEACK]);

                if (steackLength >= from && steackLength <= to) {
                    System.out.println(flowers[i][NAME] + " " + flowers[i][PRICE] + " " + flowers[i][ICE] + " " + steackLength);
                }
            }
        } else {
            System.out.println("From must be lower or equal than to");
        }
    }


    public static void main(String[] args) {
         printFlowers();

         int sum = price();
        System.out.println("Tatal sum: " + sum);
        System.out.println("----------------");
        sort();
        printFlowers();
        System.out.println("----------------");
        searchForSteack(11,19);

    }

}
