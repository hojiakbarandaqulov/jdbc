import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        String sql = "update student set name ='Alish' where id = 1;";
//        execute1(sql);
//        String sql = "insert into student(name,surname) values ('%s','%s')";
//        execute2();
//        execute3();
        execute4();
    }

    public static void execute1(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = con.createStatement();
            boolean hasResult = statement.execute(sql);
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    System.out.println("id" + resultSet.getInt("id") + " name " + resultSet.getString("name"));
                }
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execute2() {
        try {
            String name = "Sabzi";
            String surname = "Sabziyev";

            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = con.createStatement();

            String sql = "insert into student(name,surname) values ('%s','%s')";
            sql = String.format(sql, name, surname);

            int effectedRows = statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void execute3() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = connection.createStatement();
            int effectedRow = statement.executeUpdate("delete from student  where id=8");
            System.out.println(effectedRow);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void execute4() {
        try {
            String name= "Valish";
            String surname="Valiyev";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_lesson_jon", "db_lesson_user", "1234");
            Statement statement = connection.createStatement();

            String sql = "insert into student(name,surname) values ('%s','%s')";
            sql=String.format(sql,name,surname);
            int effectedRow= statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}