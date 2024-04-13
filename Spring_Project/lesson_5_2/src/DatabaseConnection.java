import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection con;
        try {
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
