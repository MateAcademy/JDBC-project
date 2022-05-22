package com.itvdn.javastarter.test;

public class Bunch {

    static final int FLOWER_NAME = 0;
    static final int FLOWER_PRICE = 1;
    static final int FLOWER_ICE = 2;
    static final int FLOWER_STEACK = 3;

    static String[][] flowers = {
            {"Tulpan", "$20", "5", "8"},
            {"Rose", "$25", "4", "10"}
    };

    static int price() {
        int price = 0;

        for (int i = 0; i < flowers.length; i++) {
             price += Integer.parseInt(flowers[i][FLOWER_PRICE].substring(1));
        }

        return price;
    }

    static String[] getFlowerBySteack(int begin, int last) {

        String[] ourFlower = {"Not found flower from " + begin + " to "  + last};

        for (int i = 0; i < flowers.length; i++) {
            int steack = Integer.parseInt(flowers[i][FLOWER_STEACK]);

            if (steack >= begin && steack <= last) {
                ourFlower = flowers[i];
            }

        }

        return ourFlower;
    }

    static void printFlowers() {
        for (int i = 0; i < flowers.length; i++) {
            for (int j = 0; j < flowers[i].length; j++) {
                System.out.print(flowers[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void addFlower(String name, int price, int ice, int steck) {
        String[] flower = {name, "$" + price,"" + ice, String.valueOf(steck)};
        String[][] newArr = new String[flowers.length + 1][4];

        for (int i = 0; i < flowers.length; i++) {
            newArr[i] = flowers[i];
        }

        newArr[flowers.length] = flower;
        flowers = newArr;
    }

    static void sort() {
        for (int j = 0; j < flowers.length; j++) {
            for (int i = 0; i < flowers.length - 1; i++) {
                int currentIce = Integer.parseInt(flowers[i][FLOWER_ICE]);
                int nextIce = Integer.parseInt(flowers[i + 1][FLOWER_ICE]);

                if (currentIce > nextIce) {
                    String[] tmp = flowers[i];
                    flowers[i] = flowers[i + 1];
                    flowers[i + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {

//        String[] result = getFlowerBySteack(7, 9);
//
//        for (String value : result) {
//            System.out.print(value + " ");
//        }

        printFlowers();
//        sort();
        System.out.println("--------------------");
        addFlower("Romashka", 15, 5, 9);
        printFlowers();



        System.out.println("--------------------");
        addFlower("Lutik", 10, 3, 7);
        printFlowers();


        sort();

        System.out.println("------------");
        printFlowers();

    }

}
