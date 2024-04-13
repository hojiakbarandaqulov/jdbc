import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        insert("Java", "Java LongConsumer", "Hojiakbar Andaqulov", LocalDate.of(2024, 10, 9));
//        selectAll();
        selectById(8);
    }

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

    public static void selectAll() {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from article";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String authorName = resultSet.getString("author_name");

                Date publishDate = resultSet.getDate("publish_date");
                LocalDate publishedDateLocal = null;
                if (publishDate != null) {
                    publishedDateLocal = publishDate.toLocalDate();
                }
                System.out.println("id: " + id + ", title: " + title + ", authorName: " + authorName + ", publishDate: " + publishDate);
            }

        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    public static void selectById(Integer sid) {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from article where id=" + sid + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String authorName = resultSet.getString("author_name");

                Date publishDate = resultSet.getDate("publish_date");

                LocalDate publishedDateLocal = null;
                if (publishDate != null) {
                    publishedDateLocal = publishDate.toLocalDate();
                }
                System.out.println("id: " + id + ", title: " + title + ", authorName: " + authorName + ", publishDate: " + publishDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}