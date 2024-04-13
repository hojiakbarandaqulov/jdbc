import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataUtil {
    public static Connection getConnection(){
        Connection con=null;
        try {
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
//jdbc:postgresql://localhost:5432/postgres
    }
}
