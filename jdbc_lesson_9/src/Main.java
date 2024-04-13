import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select id,name from student where id>100");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(+id + " " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}