package repository;

import dto.ContactDTO;
import util.DataBaseUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {
    public boolean saveContact(ContactDTO contactDTO) {
        try {
            Connection connection = DataBaseUtil.getConnection();
            String sql = "INSERT INTO contact(name, surname, phone) values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contactDTO.getName());
            preparedStatement.setString(2, contactDTO.getSurname());
            preparedStatement.setString(3, contactDTO.getPhone());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ContactDTO getByPhone(String phone) {
        ContactDTO contactDTO = new ContactDTO();
        try {
            Connection connection = DataBaseUtil.getConnection();
            String sql = "select id, name, surname, phone from contact where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDTO;
    }

    public List<ContactDTO> getList() {
        Connection connection = null;
        List<ContactDTO> contactDTOList = new LinkedList<>();
        try {
            connection = DataBaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM  contact");
            while (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                contactDTOList.add(contactDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactDTOList;
    }

    public int delete(String phone) {
        Connection connection = null;
        try {
            connection = DataBaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contact WHERE phone = ?");
            preparedStatement.setString(1, phone);
            int effectedRows = preparedStatement.executeUpdate();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public List<ContactDTO> search(String query) {
        Connection connection = null;
        List<ContactDTO> contactDTOList = new LinkedList<>();
        try {
            connection = DataBaseUtil.getConnection();

            /*Select * from contact
            where lower(name) like '%ali%' or
                    lower(surname) like '%ali%' or
	                phone like '%ali%' */

            String sql = "SELECT * FROM contact WHERE LOWER(name) LIKE  ? OR LOWER(surname) LIKE  ? OR LOWER(phone) LIKE ?;";
            String param = "%" + query + "%";
            param = param.toLowerCase();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            preparedStatement.setString(3, param);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setId(resultSet.getInt("id"));
                contactDTO.setName(resultSet.getString("name"));
                contactDTO.setSurname(resultSet.getString("surname"));
                contactDTO.setPhone(resultSet.getString("phone"));
                contactDTOList.add(contactDTO);
            }
            return contactDTOList;
        } catch (SQLException e) {
            e.printStackTrace();
            return contactDTOList;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
