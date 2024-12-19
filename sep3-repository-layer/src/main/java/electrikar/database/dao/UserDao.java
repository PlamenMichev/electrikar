package electrikar.database.dao;

import electrikar.User;

import java.sql.SQLException;
import java.util.ArrayList;


public interface UserDao
{
  /**
   * Create a new user in the database
   * @param legalName
   * @param email
   * @param password
   * @param cpr
   * @param phone
   * @param isAdmin
   * @param isBanned
   * @return
   * @throws SQLException
   */
  User createUser(String legalName, String email, String password, String cpr, String phone, boolean isAdmin, boolean isBanned) throws SQLException;

  /**
   * Update a user in the database
   * @param user
   * @throws SQLException
   */
  void updateUser(User user) throws SQLException;

  /**
   * Get a user by its id
   * @param id
   * @return
   * @throws SQLException
   */
  User getUserById(int id) throws SQLException;

  /**
   * Delete a user by its id
   * @param id
   * @throws SQLException
   */
  void deleteUserById(int id) throws SQLException;

  /**
   * Get all users in the database
   * @return
   * @throws SQLException
   */
  ArrayList<User> getAll() throws SQLException;
}
