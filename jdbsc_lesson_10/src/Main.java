import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = con.createStatement();
            int effectedRow = statement.executeUpdate("delete from student where id>2");
            System.out.println(effectedRow);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}