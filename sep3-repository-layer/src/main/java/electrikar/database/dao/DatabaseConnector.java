package electrikar.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

  /**
   * Connector method to connect to the database
   * @return
   * @throws SQLException
   */
  public Connection connect() throws SQLException {
    return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres?currentSchema=electrikar",
            "postgres",
            "1234");
  }
}
