package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtil {

    public static void createTable() {
        String sql = "create table if not exists contact(" +
                " id serial primary key," +
                " name varchar(25) not null," +
                " surname varchar(25) not null," +
                " phone varchar(12) not null unique" +
                ")";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
