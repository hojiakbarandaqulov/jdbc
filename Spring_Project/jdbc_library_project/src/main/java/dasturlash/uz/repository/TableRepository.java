package dasturlash.uz.repository;

import dasturlash.uz.DatabaseConnection.ConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableRepository {
    public void createTables(){
        String sql="create table if not exists profile" +
                "(" +
                "    id serial primary key ," +
                "    name varchar(25) not null ," +
                "    surname varchar(25) not null ," +
                "    login varchar(25) not null unique ," +
                "    password varchar(35) not null ," +
                "    phone varchar(12) not null ," +
                "    status varchar(15) not null ," +
                "    role varchar(15) not null ," +
                "    created_date timestamp default now()" +
                ");";
        Connection connection= ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getErrorCode();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Connection connection1=ConnectionUtil.getConnection();
        String sql_1="create table if not exists category(" +
                "id serial primary key," +
                "name varchar(25) unique not null," +
                "created_date timestamp not null default now()," +
                "visible boolean default(true))";
        try {
            PreparedStatement preparedStatement = connection1.prepareStatement(sql_1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }finally {
            try {
                connection1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Connection connection2=ConnectionUtil.getConnection();
        String sql_2="create table if not exists book(" +
                "id serial primary key," +
                "title varchar(255) not null," +
                "author varchar(15)not null," +
                "category int not null," +
                "available_day int,"+
                "publish_date date)" ;
        try {
            PreparedStatement preparedStatement = connection2.prepareStatement(sql_2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Connection connection3=ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection3.prepareStatement("create table if not exists taken_book(" +
                    "id serial primary key," +
                    "studentId bigint," +
                    "bookId bigint," +
                    "created_date timestamp not null default(now())," +
                    "status varchar(255) default('ACTIVE'))");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection3.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
