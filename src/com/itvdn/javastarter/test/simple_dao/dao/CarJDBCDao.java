package com.itvdn.javastarter.test.simple_dao.dao;

import com.itvdn.javastarter.test.simple_dao.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class CarJDBCDao implements CarDAO {

    @Override
    public void add(Car car) {
         Connection connection = null;

          connection = getConnection();
          PreparedStatement statement;

        try {

            int markId = getMarkId(car.getMark(), connection);

            if (markId == -1) {
                statement = connection.prepareStatement("INSERT INTO marks(mark) VALUES (?)");
                statement.setString(1, car.getMark());
                statement.execute();
                statement = connection.prepareStatement("SELECT MAX(id) FROM marks");
                ResultSet rs = statement.executeQuery();
                rs.next();
                markId = rs.getInt(1);
            }

            statement = connection.prepareStatement("INSERT INTO cars(mark_id, model, price) VALUES (?, ?, ?)");

            statement.setInt(1, markId);
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getPrice());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getMarkId(String markName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM marks WHERE mark = ? ");
            preparedStatement.setString(1, markName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT c.id, m.mark, c.model, c.price FROM cars as c " +
                                                      "INNER JOIN marks as m ON m.id = c.mark_id ");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong(1);
                String mark = rs.getString(2);
                String model = rs.getString(3);
                int price = rs.getInt(4);
                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setPrice(price);
                allCars.add(car);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public Car getById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("SELECT m.mark, c.model, c.price FROM cars as c " +
                    "INNER JOIN marks as m ON m.id = c.mark_id WHERE c.id = ? ");

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String mark = rs.getString(1);
                String model = rs.getString(2);
                int price = rs.getInt(3);
                Car car = new Car();
                car.setId(id);
                car.setModel(model);
                car.setMark(mark);
                car.setPrice(price);
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public void updatePrice(int price, int carId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE cars SET price = ? WHERE id = ?");

            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, carId);

            int updatedValues = preparedStatement.executeUpdate();

            System.out.println("Values updated: " + updatedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void remove(String mark) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {

            int markId = getMarkId(mark, connection);
            preparedStatement = connection.prepareStatement("DELETE FROM cars WHERE mark_id = ? ");

            preparedStatement.setInt(1, markId);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carshop", "root", "Epic49$$");
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
