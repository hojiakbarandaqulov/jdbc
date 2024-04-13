import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon", "JDBC", "1234");
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from student");
    }
}