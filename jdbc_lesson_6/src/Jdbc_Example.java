import java.sql.*;

public class Jdbc_Example {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            System.out.println(id + " - " + name + " - " + surname);
        }
        connection.close();
    }
}
