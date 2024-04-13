import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        insert("Java", "Java LongConsumer", "Hojiakbar Andaqulov", LocalDate.of(2024, 10, 9));
//        selectAll();
//        selectById(8);
//        update(15, "Java Backend", "Java Java");
//        deleteById(8);
deleteAll();
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

    public static void update(Integer aId, String title, String content) {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = " update article set title='%s',content='%s' where id=%d";
            sql = String.format(sql, title, content, aId);
            int effectedRows = statement.executeUpdate(sql);
            System.out.println(effectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(Integer aId) {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            int effectedRows = statement.executeUpdate("delete from article where id=" + aId);
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAll() {
        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            int effectedRows = statement.executeUpdate("delete from article");
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}