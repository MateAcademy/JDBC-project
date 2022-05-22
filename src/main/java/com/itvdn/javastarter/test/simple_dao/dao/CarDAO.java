package com.itvdn.javastarter.test.simple_dao.dao;

import com.itvdn.javastarter.test.simple_dao.entity.Car;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public interface CarDAO {
    
    void add(Car car);
    
    List<Car> getAll();
    
    Car getById(int id);
    
    void updatePrice(int price, int carId);
    
    void remove(String mark);
    
}
