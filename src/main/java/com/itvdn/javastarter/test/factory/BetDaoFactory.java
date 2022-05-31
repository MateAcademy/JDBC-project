package com.itvdn.javastarter.test.factory;

import com.itvdn.javastarter.test.simple_dao.dao.UserDAO;
import com.itvdn.javastarter.test.simple_dao.dao.impl.UserDaoJDBCImpl;
import com.itvdn.javastarter.test.simple_dao.entity.UserVo;

/**
 * @author Sergey Klunniy
 */
public class BetDaoFactory {
    private static UserVo user;
    private static UserDAO userDAO;

    public static Object getObject(Class o) {
        String nameClazz = o.getSimpleName();

        if (nameClazz.startsWith("UserVo"))
            return getUser();

        if (nameClazz.startsWith("UserDao"))
            return getUserJDBCDao();

        return null;
    }

    private static UserVo getUser() {
        if (user == null) {
            user = new UserVo();
        }
        return user;
    }

    private static UserDAO getUserJDBCDao() {
        if (userDAO == null) {
            userDAO = new UserDaoJDBCImpl();
        }
        return userDAO;
    }
}
