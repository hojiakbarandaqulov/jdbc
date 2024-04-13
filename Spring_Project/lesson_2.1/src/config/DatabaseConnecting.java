package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecting {

  public Connection getConnection(){
      try {
          Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
          return connection;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
}
