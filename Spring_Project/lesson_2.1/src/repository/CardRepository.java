package repository;

import config.DatabaseConnecting;
import dto.CardDTO;
import dto.ProfileDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardRepository {
    private DatabaseConnecting databaseConnecting = new DatabaseConnecting();

    public void createCard(Long number,LocalDate expDate) {
        Connection connection = null;
        try {
            connection = databaseConnecting.getConnection();
            String sql = "insert into card(number,exp_date) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, number);
            preparedStatement.setDate(2, Date.valueOf(expDate));
            preparedStatement.executeUpdate();

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
    }

    public void updateProfile(ProfileDTO profileDTO) {
        Connection connection = null;
        try {
            connection = databaseConnecting.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update profile set name=? surname=?, phone=?, pswd=?,created_date=?,visible=?,status=?,role=? where id=?");
            preparedStatement.setString(1, profileDTO.getName());
            preparedStatement.setString(2, profileDTO.getSurname());
            preparedStatement.setString(3, profileDTO.getPhone());
            preparedStatement.setString(4, profileDTO.getPhone());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(profileDTO.getCreatedDate()));
            preparedStatement.setBoolean(6, profileDTO.getVisible());
            preparedStatement.setString(7, profileDTO.getStatus().name());
            preparedStatement.setString(8, profileDTO.getRole().name());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
