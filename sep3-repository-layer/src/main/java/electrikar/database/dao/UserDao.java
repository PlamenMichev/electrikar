package electrikar.database.dao;

import electrikar.User;

import java.sql.SQLException;
import java.util.ArrayList;


public interface UserDao
{



  User createUser(String legalName, String email, String password, String cpr, String phone, boolean isAdmin, boolean isBanned) throws SQLException;
  void updateUser(User user) throws SQLException;
  User getUserByUsername(String username) throws SQLException;
  void deleteUserByUsername(String username) throws SQLException;
  ArrayList<User> getAll() throws SQLException;








}
