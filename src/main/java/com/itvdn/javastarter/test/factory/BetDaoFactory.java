package com.itvdn.javastarter.test.factory;

import com.trackenshure.dao.BetDao;
import com.trackenshure.dao.HumanDao;
import com.trackenshure.dao.impl.BetDaoImpl;
import com.trackenshure.dao.impl.BetDaoJDBCImpl;
import com.trackenshure.dao.impl.HumanDaoImpl;

/**
 * @author Sergey Klunniy
 */
public class BetDaoFactory {
    private static BetDao betDao;
    private static BetDaoJDBCImpl betDaoJDBC;
    private static HumanDao humanDao;

    public static Object getObject(Class o) {
        String nameClazz = o.getSimpleName();

        if (nameClazz.startsWith("BetDaoImpl"))
            return getBetDao();

        if (nameClazz.startsWith("HumanDao"))
            return getHumanDao();

        if (nameClazz.startsWith("BetDaoJDBCImpl"))
            return getBetJDBCDao();

        return null;
    }

    private static BetDao getBetDao() {
        if (betDao == null) {
            betDao = new BetDaoImpl();
//            betDao = new BetDaoJDBCImpl();
        }
        return betDao;
    }

    private static HumanDao getHumanDao() {
        if (humanDao == null) {
            humanDao = new HumanDaoImpl();
        }
        return humanDao;
    }

    private static BetDao getBetJDBCDao() {
        if (betDaoJDBC == null) {
            betDaoJDBC = new BetDaoJDBCImpl();
//            betDao = new BetDaoJDBCImpl();
        }
        return betDaoJDBC;
    }
}
