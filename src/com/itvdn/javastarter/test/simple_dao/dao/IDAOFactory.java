package com.itvdn.javastarter.test.simple_dao.dao;

import java.sql.Connection;

/**
 * Created by Asus on 31.01.2018.
 */
public interface IDAOFactory {

    CarDAO getCarDAO();

    ClientDAO getClientDAO();


}
