package electrikar.database.dao;

import electrikar.Rental;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface RentalDao
{
Rental createRental(String regNum, int userId, Timestamp startDate, Timestamp endDate) throws SQLException;
void updateRental(Rental rental) throws SQLException;
Rental getRentalById(int id) throws SQLException;
void deleteRentalById(long id) throws SQLException;
  List<Rental> getAllRentals() throws SQLException;
}
