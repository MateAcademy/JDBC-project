package com.itvdn.javastarter.test.simple_dao.entity;

import javafx.print.Collation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Client {

    private long id;
    private String name;
    private int age;
    private List<String> phone;     // в таблице phone хранить колонку с id юзера
    private List<Car> cars;          // отдельно таблицу с id клиентами и id машинами
    private String street;          //в бд будет id street

    public static void main(String[] args) {

    }

}
