package electrikar.database.dao;

import electrikar.User;

import java.sql.*;
import java.util.ArrayList;

public class SqlUserDao implements UserDao
{

  private static SqlUserDao instance;
  private final DatabaseConnector dbConnector = new DatabaseConnector();

  public SqlUserDao() throws SQLException
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



  @Override public User createUser(String legalName, String email,
      String password, String cpr, String phone, boolean isAdmin,
      boolean isBanned) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"User\"(legal_name, email, password, cpr, phone, is_admin, is_banned) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, legalName);
      statement.setString(2, email);
      statement.setString(3, password);
      statement.setString(4, cpr);
      statement.setString(5, phone);
      statement.setBoolean(6, isAdmin);
      statement.setBoolean(7, isBanned);
      statement.executeUpdate();

      try (ResultSet generatedKeys = statement.getGeneratedKeys())
      {
        if (generatedKeys.next())
        {
          int id = generatedKeys.getInt(1);
          return new User(id, legalName, email, password, cpr, phone, isAdmin,
              isBanned);
        }
        else
        {
          throw new SQLException("Creating user failed, no ID obtained.");
        }

      }
    }
  }

  @Override public void updateUser(User user) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE \"User\" SET legal_name = ?, email = ?, password = ?, cpr = ?, phone = ?, is_admin = ?, is_banned = ? WHERE id = ?");
      statement.setString(1, user.getLegalName());
      statement.setString(2, user.getEmail());
      statement.setString(3, user.getPassword());
      statement.setString(4, user.getCpr());
      statement.setString(5, user.getPhone());
      statement.setBoolean(6, user.verifyAdmin());
      statement.setBoolean(7, user.verifyBanned());
      statement.setInt(8, user.getId());
      statement.executeUpdate();
    }
  }

  @Override public User getUserById(int id) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        String legalName = resultSet.getString("legal_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String cpr = resultSet.getString("cpr");
        String phone = resultSet.getString("phone");
        boolean isAdmin = resultSet.getBoolean("is_admin");
        boolean isBanned = resultSet.getBoolean("is_banned");
        return new User(id, legalName, email, password, cpr, phone, isAdmin, isBanned);
      }
      else {
        return null;
      }
    }

  }

  @Override public void deleteUserById(int id) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"User\" WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }

  }

  @Override public ArrayList<User> getAll() throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"User\"");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<User> users = new ArrayList<>();
      while(resultSet.next())
      {
        int id = resultSet.getInt("id");
        String legalName = resultSet.getString("legal_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String cpr = resultSet.getString("cpr");
        String phone = resultSet.getString("phone");
        boolean isAdmin = resultSet.getBoolean("is_admin");
        boolean isBanned = resultSet.getBoolean("is_banned");
        users.add(new User(id, legalName, email, password, cpr, phone, isAdmin, isBanned));
      }
      return users;
    }
  }
  }

