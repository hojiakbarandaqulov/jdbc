import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*create table image_attach(
                id serial primary key,
                name varchar(255),
                type varchar(25),
                attach_data bytea
        );*/
//        insert();
        restore();
    }
    public static void restore() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "select name,type,attach_data from image_attach";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String attach_type = resultSet.getString("type");
                InputStream attach_data = resultSet.getBinaryStream("attach_data");

                byte[] buffer = new byte[attach_data.available()];
                attach_data.read(buffer);

//                File targetFile = new File("C:/Users/Bobur_Jumaniyazov/Desktop/out" + name +"." + attach_type);
                File targetFile = new File("images/" + name + "." + attach_type);
                OutputStream outputStream = new FileOutputStream(targetFile);
                outputStream.write(buffer);
                outputStream.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static void insert(){

//        File file=new File("toString1.png");
//        try {
//            FileInputStream files=new FileInputStream(file);
//            Connection connection = DatabaseConnection.getConnection();
//            String sql = "insert into image_attach (name,type,attach_data) values(?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"toString1");
//            preparedStatement.setString(2,"png");
//            preparedStatement.setBinaryStream(3,files);
//            preparedStatement.executeUpdate();
//            connection.close();
//        } catch (FileNotFoundException | SQLException e) {
//            e.printStackTrace();
////        }
//        File file=new File("v.mp4");
//        try {
//            FileInputStream files=new FileInputStream(file);
//            Connection connection = DatabaseConnection.getConnection();
//            String sql = "insert into image_attach (name,type,attach_data) values(?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"v");
//            preparedStatement.setString(2,"mp4");
//            preparedStatement.setBinaryStream(3,files);
//            preparedStatement.executeUpdate();
//            connection.close();
//        } catch (FileNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
        File file1 = new File("OOP_1.mp4");

    }
}