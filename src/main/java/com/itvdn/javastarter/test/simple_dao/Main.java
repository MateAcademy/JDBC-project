package com.itvdn.javastarter.test.simple_dao;

import com.itvdn.javastarter.test.lib.Inject;
import com.itvdn.javastarter.test.lib.Injector;
import com.itvdn.javastarter.test.simple_dao.dao.IDAOFactory;
import com.itvdn.javastarter.test.simple_dao.dao.UserDAO;
import com.itvdn.javastarter.test.simple_dao.dao.impl.DAOFactory;

import com.itvdn.javastarter.test.simple_dao.entity.Address;
import com.itvdn.javastarter.test.simple_dao.entity.Status;
import com.itvdn.javastarter.test.simple_dao.entity.User;

/**
 * Created by Asus on 31.01.2018.
 */
public class  Main {
    @Inject
    private static User user;

    public static void main(String[] args) {
//        Main main = (Main) Injector.getInstance(Main.class);

        IDAOFactory factory = DAOFactory.getInstance();
        UserDAO clientDAO = factory.getClientDAO();
        //

        user.setUserName("Sergey");
        user.setUserAge(20);
        user.setAddress(new Address("Kievska", 50));
        user.setStatus(Status.ACTIVE);

        clientDAO.add(user);

//        IDAOFactory factory = DAOFactory.getInstance();
//        UserDAO clientDAO = factory.getClientDAO();
//
//        Client client = new Client();
//
//        clientDAO.add(client);



//        IDAOFactory factory = DAOFactory.getInstance();
//        CarDAO carDAO = factory.getCarDAO();
//
//        carDAO.updatePrice(70000, 9);
//
//                carDAO.remove("Chevrolet");

//        System.out.println(car.getId() + " " + car.getMark()
//                    + " " + car.getModel() + " " + car.getPrice());

//       Car car = new Car();
//       car.setMark("AUDI");
//       car.setModel("A8");
//       car.setPrice(150_000);
//       carDAO.add(car);

//        Car car2 = new Car();
//        car2.setMark("Mersedes");
//        car2.setModel("S500");
//        car2.setPrice(160_000);
//        carDAO.add(car);


//        List<Car> cars = carDAO.getAll();
//        for (Car car : cars) {
//            System.out.println(car.getId() + " " + car.getPrice() + " " + car.getMark() + " " + car.getModel());
//        }

//        Car car1 = carDAO.getById(9);
//        System.out.println(car1.getId() + " " + car1.getPrice() + " " + car1.getMark() + " " + car1.getModel());
    }
}
