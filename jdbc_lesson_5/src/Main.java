import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") +" "+
                    resultSet.getString("surname"));
        }
        connection.close();
    }
}