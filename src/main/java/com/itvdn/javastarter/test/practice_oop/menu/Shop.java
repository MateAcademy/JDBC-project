package com.itvdn.javastarter.test.practice_oop.menu;

import com.itvdn.javastarter.test.practice_oop.entity.Bunch;
import com.itvdn.javastarter.test.practice_oop.entity.Flower;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Asus on 22.11.2017.
 */
public class Shop {

    private Bunch bunch;

    public Shop() {
        bunch = new Bunch();
    }

    private void addFlower(Scanner sc) {

        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Price : ");
        double price = sc.nextDouble();
        System.out.print("Ice level press NOT_GOOD, MEDIUM, NICE, VERY_NICE");
        String iceLevel = sc.next();
        System.out.print("Height of steack");
        int heightOfSteack = sc.nextInt();

        Flower flower = new Flower();
        flower.setName(name);
        flower.setPrice(price);
        flower.setHeightOfSteck(heightOfSteack);
        flower.setIceLevel(Flower.IceLevel.valueOf(iceLevel));

        bunch.addFlower(flower);
    }

    private void findBySteables(Scanner sc) {
        System.out.print("Ot: ");
        int begin = sc.nextInt();
        System.out.print("Do: ");
        int end = sc.nextInt();
        List<Flower> flowersBySteack = bunch.getFlowersByStables(begin, end);

        System.out.println(flowersBySteack);

    }

    public static void main(String[] args) {
        System.out.println("Start your menu: press 1 to add Flower, 2 - count price bunch, 3 - sort flowers, " +
                "4 - search flowers by steack, 5 - exit");

        Scanner sc = new Scanner(System.in);
        Shop menu = new Shop();
        boolean isShopping = true;

        while (isShopping) {
            int res = sc.nextInt();

            switch (res) {
                case 1 :
                    menu.addFlower(sc);
                    System.out.println(menu.bunch);
                    break;
                case 2 :
                    System.out.println("Price " + menu.bunch.price());
                    break;
                case 3 :
                    menu.sort();
                    System.out.println(menu.bunch);
                    break;

                case 4 : menu.findBySteables(sc);
                      break;

                case 5: isShopping = false;


            }

        }

        System.out.println("End of the shop, Bye :)");
    }

    public void sort() {

        List<Flower> flowers = bunch.getFlowers();

        for (int i = 0; i < flowers.size(); i++) {
            for (int j = 0; j < flowers.size(); j++) {
                if (i < flowers.size() - 1) {
                    Flower.IceLevel currentIce = flowers.get(i).getIceLevel();
                    Flower.IceLevel nextIce = flowers.get(i + 1).getIceLevel();

                    if (currentIce.getId() > nextIce.getId()) {
                        Flower tmp = flowers.get(i);
                        flowers.set(i, flowers.get(i + 1));
                        flowers.set(i+1, tmp);
                    }
                }
            }
        }

    }

}
