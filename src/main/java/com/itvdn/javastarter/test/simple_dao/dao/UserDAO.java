package com.itvdn.javastarter.test.simple_dao.dao;

import com.itvdn.javastarter.test.simple_dao.entity.UserVo;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public interface UserDAO {

    void add(UserVo user);

    List<UserVo> getAll();

    UserVo getById(int id);

    void updateAge(String name , int age);

    void remove(String name);
}
