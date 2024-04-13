import org.postgresql.copy.PGCopyInputStream;

import java.io.IOException;
import java.sql.*;

public class Database {
    private PGCopyInputStream connection;

    public void addContact(User user) {
        try {
            Connection connection = DataUtil.getConnection();
            String sql = "insert into contact(name,surname,phone)values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getPhone());
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println("Contact added");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ContactList() {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Integer phone = resultSet.getInt("phone");
                Integer id = resultSet.getInt("id");
                System.out.println("id: " + id + " name: " + name + " surname: " + surname + " phone: " + phone);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchContact(String nameSearch) {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact where name like ?");
            preparedStatement.setString(1, "%" + nameSearch + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int phone = resultSet.getInt("phone");
                int id = resultSet.getInt("id");
                System.out.println("id: " + id + " name: " + name + " surname: " + surname + " phone: " + phone);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Integer delete) {
        try {
            Connection connection = DataUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from contact where id=?");
            preparedStatement.setInt(1, delete);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
