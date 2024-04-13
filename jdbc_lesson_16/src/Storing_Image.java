import javax.xml.crypto.Data;
import java.io.*;
import java.sql.*;

public class Storing_Image {
    public static void main(String[] args) {
//        retrieveImage();
//        insertFile();
        retrieveFile();
    }

    public void insertImage() {
        try {
            File file = new File("test image.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            Connection connection = DataUtil.getConnection();
            String sql = "insert into image_attach (f_name,f_type,f_data) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "test image");
            preparedStatement.setString(2, "jpg");
            preparedStatement.setBinaryStream(3, fileInputStream);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void retrieveImage() {

        try {
            Connection connection = DataUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from image_attach");
            while (resultSet.next()) {
                String fileName = resultSet.getString("f_name");
                String fileType = resultSet.getString("f_type");
                InputStream inputStream = resultSet.getBinaryStream("f_data");


                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                File file = new File("Image_test/" + fileName + " _programmer" + "." + fileType);
                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(buffer);
                outputStream.close();
            }
            connection.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertFile() {
        try {
            File file = new File("dasturlash.uz");
            InputStream inputStream = new FileInputStream(file);

            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into image_attach(f_name, f_type, f_data) values (?,?,?)");
            preparedStatement.setString(1, "dasturlash");
            preparedStatement.setString(2, "txt");
            preparedStatement.setBinaryStream(3, inputStream);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void retrieveFile() {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "select * from image_attach";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String fileName = resultSet.getString("f_name");
                String fileType = resultSet.getString("f_type");
                InputStream inputStream = resultSet.getBinaryStream("f_data");

                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);

                if (inputStream != null) {
                    String url = "files/" + fileName + "." + fileType;
                    File file = new File(url);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(buffer);
                    fileOutputStream.close();
                }
            }
            connection.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}