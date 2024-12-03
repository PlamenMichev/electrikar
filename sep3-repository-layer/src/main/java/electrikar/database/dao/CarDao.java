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

  Car createCar(String regNum, CarColor color,  CarMake make, CarModel model,
      CarType type, int price, String image, boolean hasRentals) throws SQLException;
  void updateCar(Car car) throws SQLException;
  Car getCarByReg(String reg)  throws SQLException;
  void deleteCarByReg(String reg) throws SQLException;
  ArrayList<Car> getAll() throws SQLException;
}
