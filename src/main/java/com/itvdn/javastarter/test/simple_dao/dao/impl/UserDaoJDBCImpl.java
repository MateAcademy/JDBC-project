package com.itvdn.javastarter.test.simple_dao.dao.impl;

import com.itvdn.javastarter.test.connection.MyConnection;
import com.itvdn.javastarter.test.lib.Dao;
import com.itvdn.javastarter.test.simple_dao.dao.UserDAO;
import com.itvdn.javastarter.test.simple_dao.entity.Address;
import com.itvdn.javastarter.test.simple_dao.entity.UserVo;

import java.sql.*;
import java.util.List;

//логирование, трай с ресурсами сделать, методы все доделать, коннекшин пул, сонар подключить, хибернейт,
//в отдельной ветке, репозиторий на гитхабе

@Dao
public class UserDaoJDBCImpl implements UserDAO {

    @Override
    public void add(UserVo user) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!isAddress(user.getAddress(), connection)) {
                try {
                    ps = connection.prepareStatement("insert into address (user_id, street_name, number_home) value (?,?,?)");
                    ps.setLong(1, user.getUserID());
                    ps.setString(2, user.getAddress().getNameStreet());
                    ps.setInt(3, user.getAddress().getNumberHome());
                    ps.execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<String> phones = user.getUserPhones();
            if (!isPhones(phones, connection)) {
                for (String p : phones) {
                    ps = connection.prepareStatement("INSERT INTO phone (phone_number, user_id) VALUES (?, ?)");
                    ps.setString(1, p);
                    ps.setLong(2, user.getUserID());
                    ps.execute();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    private void addUserToDB(UserVo user, Connection connection) {
//
//    }

    private boolean isAddress(Address address, Connection connection) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM address WHERE name_street = ? AND number_home = ?");
            ps.setString(1, address.getNameStreet());
            ps.setInt(2, address.getNumberHome());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPhones(List<String> phones, Connection connection) {

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM address WHERE name_street = ? AND number_home = ?");
            ps.setString(1, address.getNameStreet());
            ps.setInt(2, address.getNumberHome());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean isUser(UserVo user, Connection connection) {
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
    public List<UserVo> getAll() {
        return null;
    }

    @Override
    public UserVo getById(int id) {
        /* PreparedStatement preparedStatement = null;


        Connection connection = MyConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement("SELECT c.name, c.age, s.street_name FROM users as c " +

                    "INNER JOIN street as s ON s.street_id = c.street_id" +
                    "WHERE c.id = ? ");

            preparedStatement.setInt(1, id);


            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                String streetName = rs.getString(3);


                UserVo user = new UserVo();
                user.setUserId(id);
                user.setUserName(name);
                user.setUserAge(age);
                user.setAddress(streetName);
                user.setUserPhone(getPhoneByIdClient(id, connection));
                return user;
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
        }*/

        return null;
    }

    @Override
    public void updateAge(String name, int age) {
    }

    @Override
    public void remove(String name) {

    }

    private List<String> getPhoneByIdClient(int id, Connection connection) {
        List<String> phoneList = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT phone_number FROM phone WHERE client_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                phoneList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phoneList;

    }

    private int getStreetId(String streetName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT street_id FROM street WHERE street_name = ? ");
            preparedStatement.setString(1, streetName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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

    private long getClientId(String clientName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM clients WHERE name = ? ");
            preparedStatement.setString(1, clientName);

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
