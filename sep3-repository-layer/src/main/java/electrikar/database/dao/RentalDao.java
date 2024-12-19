package electrikar.database.dao;

import electrikar.Rental;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface RentalDao
{

  /**
   * Create a new rental in the database
   * @param regNum
   * @param userId
   * @param startDate
   * @param endDate
   * @return
   * @throws SQLException
   */
Rental createRental(String regNum, int userId, Timestamp startDate, Timestamp endDate) throws SQLException;

/**
 * Update a rental in the database
 * @param rental
 * @throws SQLException
 */
void updateRental(Rental rental) throws SQLException;

/**
 * Get a rental by its id
 * @param id
 * @return
 * @throws SQLException
 */
Rental getRentalById(int id) throws SQLException;

/**
 * Delete a rental by its id
 * @param id
 * @throws SQLException
 */
void deleteRentalById(long id) throws SQLException;

  /**
   * Get all rentals in the database
   * @return
   * @throws SQLException
   */
  List<Rental> getAllRentals() throws SQLException;
}
