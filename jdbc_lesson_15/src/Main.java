import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        prepareStatementInsertExample("Java Jdbc", "Java Master", "Alish", LocalDate.of(2024, 3, 10));
//        creatTable();
//        createTableExample();
//        insertTable(2,"Vali", "Valiyev",995092376);
//        deleteTable();

//        selectByAuthor("Al");
        updateArticle(16, "JDBC_Java", "JAVA");
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

    public static void prepareStatementInsertExample(String title, String content, String author_name, LocalDate publish_date) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = " insert into article(title,content, author_name,publish_date) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, author_name);
            preparedStatement.setDate(4, Date.valueOf(publish_date));

            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void creatTable() {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "create table employee\n" +
                    "(\n" +
                    "    id varchar primary key,\n" +
                    "    name varchar(25),\n" +
                    "    surname varchar(25),\n" +
                    "    age int\n" +
                    ");\n";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableExample() {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "create table java\n" +
                    "(\n" +
                    "    id serial primary key,\n" +
                    "    name varchar(25),\n" +
                    "    surname varchar(25),\n" +
                    "    phone int\n" +
                    ");";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTable(Integer id, String name, String surname, Integer phone) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "insert into employee(id,name,surname,age) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setInt(4, phone);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static void deleteTable(){
//        try {
//            Connection connection=DataUtil.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("drop table Student);
//            int row = preparedStatement.executeUpdate();
//            System.out.println(row);
//            connection.close();
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
//
//    }

    public static void selectByAuthor(String authorName) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "select * from article where author_name like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + authorName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author_name");

                System.out.println("id = " + id + " title = " + title + " authorName = " + author);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateArticle(Integer aId, String title, String content) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "update article set title = ?, content=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, aId);
            int effectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}