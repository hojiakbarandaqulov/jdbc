import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        createTable();
//        insert("Interface", "Java Interface turlari", "Hojiakbar Andaqulov", LocalDate.of(2024, 10, 9));
        insert("Java", "Java LongConsumer", "Hojiakbar Andaqulov", LocalDate.of(2024, 10, 9));

    }

    public static void createTable() {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "create table article( " +
                    "    id serial primary key, " +
                    "    title varchar(225), " +
                    "    content text, " +
                    "    author_name varchar(225), " +
                    "    publish_date date );";
            int effectedRows = statement.executeUpdate(sql);
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*  public static void insert(String title, String content, String author_name, LocalDate publish_date) {
          try {
              Connection connection = DataUtil.getConnection();
              Statement statement = connection.createStatement();
              String sql = " insert into article(title,content, author_name,publish_date) values('" + title + "','" + content + "','" + author_name + "','" + publish_date + "')";
              System.out.println(sql);
              statement.executeUpdate(sql);
              connection.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }

      }*/
    public static void insert(String title, String content, String author_name, LocalDate publish_date) {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = " insert into article(title,content, author_name,publish_date) values('%s','%s','%s','%s')";
            sql = String.format(sql, title, content, author_name, publish_date.toString());

            System.out.println(sql);
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}