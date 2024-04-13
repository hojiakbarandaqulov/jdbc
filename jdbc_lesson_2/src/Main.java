import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = connection.createStatement();
           int resultSet= statement.executeUpdate(
                   "delete from student where id =4");
            System.out.println(resultSet);
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " " +
//                        resultSet.getString("surname"));
//            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }}