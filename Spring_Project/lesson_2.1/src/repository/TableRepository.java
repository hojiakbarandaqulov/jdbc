package repository;

import config.DatabaseConnecting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableRepository {
    public void createTable() {
        Connection connection=null;
        try {
        DatabaseConnecting databaseConnecting = new DatabaseConnecting();
        connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("create table  if not exists profile(" +
                    "id serial primary key," +
                    "name varchar(25)not null," +
                    "surname varchar(25) not null," +
                    "phone varchar(13) unique not null," +
                    "pswd varchar not null," +
                    "created_date timestamp default now()," +
                    "visible bool default true," +
                    "status varchar(20)not null," +
                    "role varchar(20)not null)"
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void cardTable() {
        Connection connection=null;
        try {
        DatabaseConnecting databaseConnecting = new DatabaseConnecting();
        connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("create table  if not exists card(" +
                    "id serial primary key," +
                    "number bigint not null unique," +
                    "exp_date date not null ," +
                    "balance bigint default(0)," +
                    "status varchar(255) default('ACTIVE')," +
                    "visible boolean default(true)," +
                    "created_date timestamp default(now())" +
                    ")");
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
