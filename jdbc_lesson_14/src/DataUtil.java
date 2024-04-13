import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataUtil {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}