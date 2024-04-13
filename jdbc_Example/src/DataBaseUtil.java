import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DataBaseUtil {
    public static void createTable() {
        try {
            String sql = "create table if not exists contact (" +
                    "id serial primary key ," +
                    "name varchar(25) not null," +
                    "surname varchar(25) not null," +
                    "phone varchar(12) not null unique" +
                    ")";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public static void addContact(Contact addContact) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into contact(id,name,surname,phone)values (?,?,?,?)");
            preparedStatement.setString(1, addContact.getName());
            preparedStatement.setString(2, addContact.getSurname());
            preparedStatement.setString(3, addContact.getPhone());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ContactList() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Integer phone = resultSet.getInt("phone");
                System.out.println(id + " " + name + " " + surname + " " + phone);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteContact(Integer deleteId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from contact where id=?");
            preparedStatement.setInt(1, deleteId);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Contact> searchContact(String searchName) {
        List<Contact>contactList=new LinkedList<>();
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_db", "lesson_db", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where  name like ? or lower(surname) like ? or lower(phone) like ?");
            String param="%" + searchName + " % ";
            preparedStatement.setString(1,param);
            preparedStatement.setString(2,param);
            preparedStatement.setString(3,param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Contact addContact = new Contact();
                addContact.setName("name");
                addContact.setSurname("surname");
                addContact.setPhone("phone");
                contactList.add(addContact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (contactList!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return contactList;
    }
}
