package com.itvdn.javastarter.test.simple_dao;

import com.itvdn.javastarter.test.simple_dao.dao.CarDAO;
import com.itvdn.javastarter.test.simple_dao.dao.impl.DAOFactory;
import com.itvdn.javastarter.test.simple_dao.dao.IDAOFactory;
import com.itvdn.javastarter.test.simple_dao.entity.Car;

/**
 * Created by Asus on 31.01.2018.
 */
public class  Main {

    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();

        carDAO.updatePrice(70000, 9);

                carDAO.remove("Chevrolet");

//        System.out.println(car.getId() + " " + car.getMark()
//                    + " " + car.getModel() + " " + car.getPrice());

       Car car = new Car();
       car.setMark("AUDI");
       car.setModel("A8");
       car.setPrice(150_000);
       carDAO.add(car);

//        Car car2 = new Car();
//        car2.setMark("Mersedes");
//        car2.setModel("S500");
//        car2.setPrice(160_000);
//        carDAO.add(car);


//        List<Car> cars = carDAO.getAll();
//        for (Car car : cars) {
//            System.out.println(car.getId() + " " + car.getPrice() + " " + car.getMark() + " " + car.getModel());
//        }

        Car car1 = carDAO.getById(9);
        System.out.println(car1.getId() + " " + car1.getPrice() + " " + car1.getMark() + " " + car1.getModel());

    }

}
