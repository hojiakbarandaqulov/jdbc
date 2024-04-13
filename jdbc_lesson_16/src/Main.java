import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        createTable();
//        dropTable();"
//        insertQuery("Interface", "Java backend lessons", "Hojiakbar", LocalDate.of(1212, 2, 12));
//        selectQuery("a");
//        updateQuery("+998995092376","Ali","Aliyev",1);

//        deleteQuery(2);


    }

    public static void createTable() {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("create table java(\n" +
                    "    id serial primary key,\n" +
                    "    name varchar(25),\n" +
                    "    surname varchar(25),\n" +
                    "    age int,\n" +
                    "    phone varchar(25),\n" +
                    "    birth_date date\n" +
                    ");");
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("drop table article ;");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertQuery(String title, String content, String authorName, LocalDate publishDate) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = " insert into article(title,content,author_name,publish_date) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, authorName);
            preparedStatement.setDate(4, Date.valueOf(publishDate));

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectQuery(String Title) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "select * from article where article.title like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + Title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author_name = resultSet.getString("author_name");
                System.out.println("id = " + id + " title = " + title + " author_name = " + author_name);
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateQuery(String phone, String Name, String surname, Integer empId) {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update employee set phone=?, name=?,surname=?  where id=?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, Name);
            preparedStatement.setString(3, surname);
            preparedStatement.setInt(4, empId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteQuery(Integer aId) {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from article where id=?");
            preparedStatement.setInt(1, aId);

            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

