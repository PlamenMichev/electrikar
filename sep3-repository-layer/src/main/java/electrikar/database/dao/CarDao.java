package electrikar.database.dao;

import electrikar.Car;
import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarDao
{

  /**
   * Create a new car in the database
   * @param regNum
   * @param color
   * @param make
   * @param model
   * @param type
   * @param price
   * @param image
   * @param hasRentals
   * @return
   * @throws SQLException
   */
  Car createCar(String regNum, CarColor color,  CarMake make, CarModel model,
      CarType type, int price, String image, boolean hasRentals) throws SQLException;

  /**
   * Update a car in the database
   * @param oldReg
   * @param car
   * @throws SQLException
   */
  void updateCar(String oldReg, Car car) throws SQLException;

  /**
   * Get a car by its registration number
   * @param reg
   * @return
   * @throws SQLException
   */
  Car getCarByReg(String reg)  throws SQLException;

  /**
   * Delete a car by its registration number
   * @param reg
   * @throws SQLException
   */
  void deleteCarByReg(String reg) throws SQLException;

  /**
   * Get all cars in the database
   * @return
   * @throws SQLException
   */
  ArrayList<Car> getAll() throws SQLException;
}
