import java.sql.*;

public class ContactRepository {
    public boolean saveContact(Contact contact) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into contact(name,surname,phone)values (?,?,?,?)");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Contact getByPhone(String phone){
        Contact contact=null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where phone=?");
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                 contact = new Contact();
                 Integer id =resultSet.getInt("id");
                 String name=resultSet.getString("name");
                 String surname=resultSet.getString("surname");
                 String p=resultSet.getString("surname");

                 contact.setName(resultSet.getString("name"));
                 contact.setSurname(resultSet.getString("surname"));
                 contact.set
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
