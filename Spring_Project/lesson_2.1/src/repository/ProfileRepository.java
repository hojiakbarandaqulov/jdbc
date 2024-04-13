package repository;

import config.DatabaseConnecting;
import dto.ProfileDTO;
import enoms.ProfileRole;
import enoms.ProfileStatus;

import java.sql.*;

public class ProfileRepository {
    private DatabaseConnecting databaseConnecting = new DatabaseConnecting();
    private ProfileDTO profileDTO = new ProfileDTO();
    public void createWithPSS(ProfileDTO dto) {
        Connection connection = null;
        try {
            DatabaseConnecting databaseConnecting = new DatabaseConnecting();
            connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into profile(name,surname,phone,pswd,created_date,visible,status,role)" +
                    "values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getSurname());
            preparedStatement.setString(3, dto.getPhone());
            preparedStatement.setString(4, dto.getPswd());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(dto.getCreatedDate()));
            preparedStatement.setBoolean(6, dto.getVisible());
            preparedStatement.setString(7, dto.getStatus().name());
            preparedStatement.setString(8, dto.getRole().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ProfileDTO getProfileByPhone(String phone, String password) {

        ProfileDTO profileDTO = null;
        Connection connection = null;
        try {
            connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where phone = ? and pswd= ?");
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profileDTO = new ProfileDTO();
                profileDTO.setId(resultSet.getInt("id"));
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPhone(resultSet.getString("phone"));
                profileDTO.setPswd(resultSet.getString("pswd"));
                profileDTO.setCreatedDate((resultSet.getTimestamp("created_date").toLocalDateTime()));
                profileDTO.setVisible(resultSet.getBoolean("visible"));
                profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return profileDTO;
    }

    public ProfileDTO getByRegistration(String name, String surname, String phone, String pswd) {
        ProfileDTO profileDTO = null;
        Connection connection = null;
        connection = databaseConnecting.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from profile where name = ? and surname = ? and phone = ? and pswd= ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, pswd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profileDTO = new ProfileDTO();
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPhone(resultSet.getString("phone"));
                profileDTO.setPswd(resultSet.getString("pswd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return profileDTO;
    }

    public void getById(Integer Id) {
        Connection connection = null;
        try {
            connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from card where id = ?");
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setId(resultSet.getInt("id"));
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPhone(resultSet.getString("phone"));
                profileDTO.setPswd(resultSet.getString("pswd"));
                profileDTO.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                profileDTO.setVisible(resultSet.getBoolean("visible"));
                profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
