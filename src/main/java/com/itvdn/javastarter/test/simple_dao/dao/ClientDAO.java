package com.itvdn.javastarter.test.simple_dao.dao;

import com.itvdn.javastarter.test.simple_dao.entity.User;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public interface ClientDAO {

    void add(User user);

    List<User> getAll();

    User getById(int id);

    void updateAge(String name , int age);

    void remove(String name);
}
