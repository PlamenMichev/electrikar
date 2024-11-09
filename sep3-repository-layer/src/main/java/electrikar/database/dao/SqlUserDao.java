package electrikar.database.dao;

import electrikar.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlUserDao implements UserDao{

  private static SqlUserDao instance;

  private SqlUserDao() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized SqlUserDao getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new SqlUserDao();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=electrikar",
        "postgres", "Plamen123");
  }


  @Override public User createUser(String legalName, String email, String password, String cpr, String phone, boolean isAdmin, boolean isBanned) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"User\"(legal_name, email, password, cpr, phone, is_admin, is_banned) VALUES (?, ?, ?, ?, ?, ?, ?)");
      statement.setString(1, legalName);
      statement.setString(2, email);
      statement.setString(3, password);
      statement.setString(4, cpr);
      statement.setString(5, phone);
      statement.setBoolean(6, isAdmin);
      statement.setBoolean(7, isBanned);
      statement.executeUpdate();
      return new User(legalName, email, password, cpr, phone, isAdmin, isBanned);

    }

  }

  @Override public void updateUser(User user) throws SQLException
  {

  }

  @Override public User getUserByUsername(String username) throws SQLException
  {
    return null;
  }

  @Override public void deleteUserByUsername(String username)
      throws SQLException
  {

  }

  @Override public ArrayList<User> getAll() throws SQLException
  {
    return null;
  }
}
