package com.itvdn.javastarter.test.simple_dao.dao.impl;

import com.itvdn.javastarter.test.simple_dao.dao.ClientDAO;
import com.itvdn.javastarter.test.simple_dao.entity.Client;

import java.sql.*;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {
    @Override
    public void add(Client client) {
        Connection connection = getConnection();
        PreparedStatement ps;

        try {
            int clientId = getClientId(client.getStreet(), connection);

            if (streetId == -1) {
                ps = connection.prepareStatement("INSERT INTO street(street_name) values (?) ");
                ps.setString(1, client.getStreet());
                ps.execute();

                ps = connection.prepareStatement("select max(street_id) from street");
                ResultSet rs = ps.executeQuery();

                streetId = rs.getInt(1);
            }

// проверим уникальность данных по столбцам name и street_fk_id

            ps = connection.prepareStatement()


            ps = connection.prepareStatement("insert into clients(name, age, street_fk_id) value (?,?,?)");
            ps.setString(1, client.getName());
            ps.setInt(2, client.getAge());
            ps.setInt(3, streetId);
            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getStreetId(String streetName, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("select street_id from street where street_name = ?");
            ps.setString(1, streetName);

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
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public void updateAge(String name, int age) {
    }

    @Override
    public void remove(String name) {

    }

    private static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/carsshop";
        String userName = "root";
        String password = "Epic49$$";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getClientId(String clientName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM clients WHERE name = ? ");
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
}
