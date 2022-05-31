package com.itvdn.javastarter.test.simple_dao.entity;

import com.itvdn.javastarter.test.lib.Dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
@Dao
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private long userID;
    private String userName;
    private int userAge;
    private List<String> userPhones;  // в таблице phone хранить колонку с id юзера
    private List<Car> userCars;       // отдельно таблицу с id клиентами и id машинами
//    private String street;          //в бд будет id street, на одной улице могут жить несколько клиентов
    private Address address;
    private Status status;

}
