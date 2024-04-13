package dasturlash.uz.repository;

import dasturlash.uz.DatabaseConnection.ConnectionUtil;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;

import java.sql.*;

public class ProfileRepository {
    public Profile getByLogin(String login) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id,name,surname,login,password,phone,status,role,created_date from profile where login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profile.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profile.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                return profile;
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
        return null;
    }
    public int create(Profile profile) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into profile(name,surname,login,password,phone,status,role,created_date)values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getLogin());
            preparedStatement.setString(4, profile.getPassword());
            preparedStatement.setString(5, profile.getPhone());
            preparedStatement.setString(6, profile.getStatus().name());
            preparedStatement.setString(7, profile.getRole().name());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(profile.getCreatedDate()));

            int effectedRows = preparedStatement.executeUpdate();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Profile getProfileByPhone(String phone) {
        Connection connection = null;
        Profile profile = null;
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "select * from profile where phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setPassword(resultSet.getString("pswd"));

                Timestamp timestamp = resultSet.getTimestamp("created_date");
                profile.setCreatedDate(timestamp.toLocalDateTime());

                String status = resultSet.getString("status");
                profile.setStatus(ProfileStatus.valueOf(status));

                String role = resultSet.getString("role");
                profile.setRole(ProfileRole.valueOf(role));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return profile;
    }
}
