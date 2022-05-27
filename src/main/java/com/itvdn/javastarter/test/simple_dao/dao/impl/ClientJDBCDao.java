package com.itvdn.javastarter.test.simple_dao.dao.impl;

import com.itvdn.javastarter.test.connection.MyConnection;
import com.itvdn.javastarter.test.simple_dao.dao.ClientDAO;
import com.itvdn.javastarter.test.simple_dao.entity.User;

import java.sql.*;
import java.util.List;

//логирование, трай с ресурсами сделать, методы все доделать, коннекшин пул, сонар подключить, хибернейт,
//в отдельной ветке, репозиторий на гитхабе

public class ClientJDBCDao implements ClientDAO {
    @Override
    public void add(User user) {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps;

        try {
//todo сделать проверку есть ли такой юзер
            getUserId(user, connection)

            //add User
            ps = connection.prepareStatement("insert into users(name, age) value (?,?)");
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getUserAge());
            ps.execute();

            ps = connection.prepareStatement("select max(user_id) from users");
            ResultSet rs = ps.executeQuery();
            long userId = rs.getLong(1);
            user.setUserID(userId);

            //add Address
            String nameStreet = user.getAddress().getNameStreet();
            int numberHome = user.getAddress().getNumberHome();
            //здесь проверка, есть ли такой юзер с адрессом в БД
            long streetId = getStreetId(userId, nameStreet, numberHome, connection);

            if (streetId == -1) {
                ps = connection.prepareStatement("INSERT INTO address(user_id, street_name, number_home) values (?,?,?)");
                ps.setLong(1, userId);
                ps.setString(2, nameStreet);
                ps.setInt(3, numberHome);
                ps.execute();
//              ps = connection.prepareStatement("select max(street_id) from street");
//              ResultSet rs = ps.executeQuery();

//              streetId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getStreetId(long userId, String streetName, int numberHome, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("select street_id from street where user_id = ?, street_name = ? " +
                    "and number_home = ?");
            ps.setLong(1, userId);
            ps.setString(2, streetName);
            ps.setInt(3, numberHome);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void updateAge(String name, int age) {
    }

    @Override
    public void remove(String name) {

    }

    private int getUserId(String userName, long userAge, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE name = ? and age = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setLong(1, userAge);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
