package SQL_Injection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Profile profile = authorization("alish", "alishmiman");
        System.out.println(profile);
    }
    public static Profile authorization(String login, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            String sql = "select * from profile where login=? and  password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            Profile profile = null;
            System.out.println(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile();
                profile.setName(resultSet.getString("name"));
                profile.setSurname(resultSet.getString("surname"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
            }
            connection.close();
            return profile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
