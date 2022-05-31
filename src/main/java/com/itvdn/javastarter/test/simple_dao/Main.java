package com.itvdn.javastarter.test.simple_dao;

import com.itvdn.javastarter.test.lib.Inject;
import com.itvdn.javastarter.test.lib.Injector;
import com.itvdn.javastarter.test.simple_dao.dao.UserDAO;

import com.itvdn.javastarter.test.simple_dao.entity.Address;
import com.itvdn.javastarter.test.simple_dao.entity.Status;
import com.itvdn.javastarter.test.simple_dao.entity.UserVo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class  Main {

    @Inject
    private static UserVo userVo;

    @Inject
    private static UserDAO userDao;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Injector.getInstanse(Main.class);

//        IDAOFactory factory = DAOFactory.getInstance();
//        UserDAO clientDAO = factory.getClientDAO();    // new UserDaoJDBCImpl()

        userVo.setUserName("Sergey");
        userVo.setUserAge(20);
        userVo.setAddress(new Address("Kievska", 50));
        userVo.setStatus(Status.ACTIVE);

        List<String> phones = new ArrayList<>();
        phones.add("333");
        phones.add("444");
        userVo.setUserPhones(phones);

//        clientDAO.add(userVo);
        userDao.add(userVo);
        System.out.println(userVo);

    }
}
