package com.itvdn.javastarter.test.old_practice;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by Asus on 03.11.2017.
 */
public class Airlines {

    private static final int PLANE_PASSENGERS_SEATS_INDEX = 1;
    private static final int PLANE_BAGGAGE_INDEX = 2;
    private static final int PLANE_CONSUMPTION_INDEX = 4;
    private static final int CB_PARAMETERS_INDEX = 0;
    private static final int CB_ELEMENT_INDEX = 1;
    private static final int CB_PARAMETERS_INITIAL_RESULT_INDEX = 0;
    private static String[][] planes = {
            { "Boeing 737-200", "0", "300", "6500", "0.4" },
            { "Boeing 737-400", "385", "3", "6500", "0.6" },
            { "Airbus 321", "180", "3", "5500", "0.4" },
            { "TU 154", "200", "3", "0", "2.0" }
    };

    private static String reduce(String[][] array, Function<String[][], String> f, String initialValue) {
        String result = initialValue;

        for( String[] el: array ) {
            String[][] args = { { result }, el };
            result =  f.apply(args);
        }

        return result;
    };

    private static String plane_description_callback(String[][] args) {
        String initialValue = args[CB_PARAMETERS_INDEX][CB_PARAMETERS_INITIAL_RESULT_INDEX];
        String format = "Название: %s, пассажиров: %s, грузов (т): %s, дальность (км): %s, расход (т/час): %s\n";

        return initialValue + String.format(format, args[CB_ELEMENT_INDEX]);
    };

    private static String passenger_capacity_callback(String[][] args) {
        Integer initialValue = Integer.parseInt(args[CB_PARAMETERS_INDEX][CB_PARAMETERS_INITIAL_RESULT_INDEX]);

        return Integer.toString(initialValue + Integer.parseInt(args[CB_ELEMENT_INDEX][PLANE_PASSENGERS_SEATS_INDEX]));
    };

    private static String baggage_capacity_callback(String[][] args) {
        Integer initialValue = Integer.parseInt(args[CB_PARAMETERS_INDEX][CB_PARAMETERS_INITIAL_RESULT_INDEX]);

        return Integer.toString(initialValue + Integer.parseInt(args[CB_ELEMENT_INDEX][PLANE_BAGGAGE_INDEX]));
    };

    private static void addPlane() {
        int newPlaneIndex = planes.length;
        String[] labels = {
                "Напишите название самолета и нажмите Enter:",
                "Напишите вместимость пассажиров и нажмите Enter:",
                "Напишите вместимость груза/багажа (т) и нажмите Enter:",
                "Напишите дальность полета и нажмите Enter:",
                "Напишите расход топлива (т/час) и нажмите Enter:"
        };
        Scanner scanner = new Scanner(System.in);

        planes = Arrays.copyOf(planes, newPlaneIndex + 1);
        planes[newPlaneIndex] = new String[5];

        for( int i = 0; i < labels.length; i++ ) {
            System.out.println(labels[i]);
            planes[newPlaneIndex][i] = scanner.nextLine();
        }
    }

    private static void findPlane() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Минимум потребления:");
        double minConsumption = scanner.nextDouble();
        System.out.println("Максимум потребления:");
        double maxConsumption = scanner.nextDouble();

        String[][] result = Arrays.stream(planes).filter(el ->
                Double.parseDouble(el[PLANE_CONSUMPTION_INDEX]) >= minConsumption &
                        Double.parseDouble(el[PLANE_CONSUMPTION_INDEX]) <= maxConsumption).toArray(String[][]::new);

        System.out.println("Результат поиска: \n" + reduce(result, el -> plane_description_callback(el), ""));
    }

    public static void main(String[] args) {
        boolean otherCharacter = false;
        Scanner scanner = new Scanner(System.in);

        while (!otherCharacter) {
            System.out.println("-------------------------------");
            System.out.println("Весь парк: \n" + reduce(planes, el -> plane_description_callback(el), ""));
            System.out.println("Общая вместимость: " + reduce(planes, el -> passenger_capacity_callback(el), "0"));
            System.out.println("Общая грузоподъемность: " + reduce(planes, el -> baggage_capacity_callback(el), "0"));

            System.out.println("Чтобы добавить самолет, нажмите 1");
            System.out.println("Чтобы найти самолет, нажмите 2");
            char c = scanner.next().charAt(0);
            switch(c) {
                case '1':
                    addPlane();
                    break;
                case '2':
                    findPlane();
                    break;
                default:
                    otherCharacter = true;
            }
        }
        System.out.println("Пока!");
    }

}
