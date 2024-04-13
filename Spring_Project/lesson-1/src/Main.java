import java.sql.*;

public class Main {
    public static void main(String[] args)  {
//        getAll();

        /* Student student = getById(1);
        System.out.println(student.toString());*/

       /* Student student = new Student();
        student.setName("Ali");
        student.setSurname("Aliyev");
        update(1, student);*/

        Student student = new Student();
        student.setName("Alishjon");
        student.setSurname("a'); delete from student;--");
        save(student);


    }



    public static Student getById(Integer id){
        Connection connection = null; // 2
        Student student = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/byte_lesson_db", "lesson_user", "123456");
            Statement statement = connection.createStatement(); //3
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student WHERE id = " + id); //4
            if (resultSet.next()){
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public static void getAll(){
        try {
            Class.forName("org.postgresql.Driver"); //1
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/byte_lesson_db", "lesson_user", "123456"); // 2
            Statement statement = connection.createStatement(); //3
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student"); //4
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") +
                        "-" + resultSet.getString("name") +
                        "-" + resultSet.getString("surname"));
            }
            connection.close(); //5
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void update(Integer id, Student student){
        Connection connection = null; // 2
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/byte_lesson_db", "lesson_user", "123456");
            Statement statement = connection.createStatement(); //3
            String sql = "UPDATE student SET name = '%s', surname = '%s' WHERE id = %d";
            sql = String.format(sql, student.getName(), student.getSurname(), id);
            System.out.println(sql);

            int effectedRows = statement.executeUpdate(sql); //4
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Student student) {
        Connection con = null; // <2>
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/byte_lesson_db", "postgres", "root");
            Statement statement = con.createStatement(); // <3>

            String sql = "insert into  student (name,surname) values('%s','%s')";
            sql = String.format(sql, student.getName(), student.getSurname());
            System.out.println(sql);
            int effectedRow = statement.executeUpdate(sql); // <4>
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}