package com.itvdn.javastarter.test.simple_dao.dao.impl;

import com.itvdn.javastarter.test.connection.MyConnection;
import com.itvdn.javastarter.test.simple_dao.dao.UserDAO;
import com.itvdn.javastarter.test.simple_dao.entity.Address;
import com.itvdn.javastarter.test.simple_dao.entity.User;

import java.sql.*;
import java.util.List;

//логирование, трай с ресурсами сделать, методы все доделать, коннекшин пул, сонар подключить, хибернейт,
//в отдельной ветке, репозиторий на гитхабе

public class UserJDBCDao implements UserDAO {

    @Override
    public void add(User user) {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps;

        try {

            // проверка есть ли такой юзер
            if (!isUser(user, connection)) {
                try {
                    ps = connection.prepareStatement("insert into users(user_name, user_age, status) value (?,?,?)");
                    ps.setString(1, user.getUserName());
                    ps.setInt(2, user.getUserAge());
                    ps.setString(3, user.getStatus().toString());
                    ps.execute();

                    ps = connection.prepareStatement("select max(user_id) from users");
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    long userId = rs.getLong(1);
                    user.setUserID(userId);
                } catch (Exception ex) {
                }
            }

            long userId = user.getUserID();

            //add User to Db

            //get Address from User
            Address userAddress = user.getAddress();
            String nameStreet = userAddress.getNameStreet();
            int numberHome = userAddress.getNumberHome();
            //здесь проверка, есть ли такой юзер с адрессом в БД
            long streetId = getStreetId(userId, nameStreet, numberHome, connection);

            //add Address to Db
            if (streetId == -1) {
                ps = connection.prepareStatement("INSERT INTO address(user_id, name_street, number_home) values (?,?,?)");
                ps.setLong(1, userId);
                ps.setString(2, nameStreet);
                ps.setInt(3, numberHome);
                ps.execute();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void addUserToDB(User user, Connection connection) {
//
//    }

    private boolean isUser(User user, Connection connection) {
        String sql = "SELECT * FROM users where user_name = ? and user_age = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getUserAge());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ps = connection.prepareStatement("select user_id from users  where user_name = ? and user_age = ? and status = ?");
                ps.setString(1, user.getUserName());
                ps.setInt(2, user.getUserAge());
                ps.setString(3, user.getStatus().toString());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                long userId = rs2.getLong(1);
                user.setUserID(userId);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getStreetId(long userId, String streetName, int numberHome, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("select adress_id from address where user_id = ? and name_street = ? " +
                    "and number_home = ?");
            ps.setLong(1, userId);
            ps.setString(2, streetName);
            ps.setInt(3, numberHome);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            //System.out.println("ошибка в методе getStreetId " + e.getMessage());
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
