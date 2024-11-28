package electrikar.database.dao;

import electrikar.Rental;
import electrikar.enums.RentalStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlRentalDao implements RentalDao {
  private static SqlRentalDao instance;
  private final DatabaseConnector dbConnector = new DatabaseConnector();

  public SqlRentalDao() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized SqlRentalDao getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new SqlRentalDao();
    }
    return instance;
  }


  @Override public Rental createRental(String regNum, int userId, Timestamp startDate, Timestamp endDate) throws SQLException
  {

    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Rental\"(car_reg, customer_id, start_date, end_date, drop_date, status, customer_comment, organizer_comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, regNum);
      statement.setInt(2, userId);
      statement.setTimestamp(3, startDate);
      statement.setTimestamp(4, endDate);
      statement.setTimestamp(5, endDate);
      statement.setInt(6, RentalStatus.valueOf(1).ordinal());
      statement.setString(7, null);
      statement.setString(8, null);
      statement.executeUpdate();

      try (ResultSet generatedKeys = statement.getGeneratedKeys())
      {
        if (generatedKeys.next())
        {
          int id = generatedKeys.getInt(1);
          return new Rental(id, regNum, userId, startDate, endDate,endDate, RentalStatus.PENDING, null, null);

        }
        else
        {
          throw new SQLException("Creating rental failed, no ID obtained.");
        }

      }
    }

  }

  @Override public void updateRental(Rental rental) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"Rental\" SET car_reg = ?, customer_id = ?, start_date = ?, end_date = ?, drop_date = ?, status = ?, customer_comment = ?, organizer_comment = ? WHERE id = ?");
      statement.setString(1, rental.getCarRegNumber());
      statement.setInt(2, rental.getUserId());
      statement.setTimestamp(3, rental.getStartDate());
      statement.setTimestamp(4, rental.getEndDate());
      statement.setTimestamp(5, rental.getDropDate());
      statement.setString(6, rental.getStatus().toString());
      statement.setString(7, rental.getCustomerComment());
      statement.setString(8, rental.getOrganizerComment());
      statement.setInt(9, (int) rental.getId());
      statement.executeUpdate();
    }

  }

  @Override public Rental getRentalById(int id) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Rental\" WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        String carRegNumber = resultSet.getString("car_reg");
        int userId = resultSet.getInt("customer_id");
        Timestamp startDate = resultSet.getTimestamp("start_date");
        Timestamp endDate = resultSet.getTimestamp("end_date");
        Timestamp dropDate = resultSet.getTimestamp("drop_date");
        RentalStatus status = RentalStatus.valueOf(resultSet.getString("status"));
        String customerComment = resultSet.getString("customer_comment");
        String organizerComment = resultSet.getString("organizer_comment");
        return new Rental(id, carRegNumber, userId, startDate, endDate, dropDate, status, customerComment, organizerComment);
      }
      else {
        return null;
      }
    }

  }

  @Override public void deleteRentalById(long id) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Rental\" WHERE id = ?");
      statement.setInt(1, (int)id);
      statement.executeUpdate();
    }

  }

  @Override public List<Rental> getAllRentals() throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Rental\"");
      ResultSet resultSet = statement.executeQuery();
      List<Rental> rentals = new ArrayList<>();
      while(resultSet.next())
      {
        int id = resultSet.getInt("id");
        String carRegNumber = resultSet.getString("car_reg");
        int userId = resultSet.getInt("customer_id");
        Timestamp startDate = resultSet.getTimestamp("start_date");
        Timestamp endDate = resultSet.getTimestamp("end_date");
        Timestamp dropDate = resultSet.getTimestamp("drop_date");
        RentalStatus status = RentalStatus.valueOf(resultSet.getInt("status"));
        String customerComment = resultSet.getString("customer_comment");
        String organizerComment = resultSet.getString("organizer_comment");
        rentals.add(new Rental(id, carRegNumber, userId, startDate, endDate, dropDate, status, customerComment, organizerComment));
      }
      return rentals;
    }
  }
}
