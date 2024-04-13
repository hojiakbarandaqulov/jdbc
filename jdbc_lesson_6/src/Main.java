import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon","db_lesson_user","1234");
        Statement statement=connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from movie");
        while (resultSet.next()){
           Integer id= resultSet.getInt("id");
           String title=resultSet.getString("title");
           Long duration= resultSet.getLong("duration");
           LocalDateTime createData=resultSet.getTimestamp("created_date").toLocalDateTime();
           LocalDate publishDate = resultSet.getDate("publish_date").toLocalDate();
           Double rating = resultSet.getDouble("rating");
            System.out.println(id+" | "+ title+" | "+ duration+" | "+ createData+" | "+ publishDate+" | "+rating);

        }
        connection.close();


    }
}