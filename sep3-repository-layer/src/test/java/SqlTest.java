import electrikar.Car;
import electrikar.Rental;
import electrikar.database.dao.SqlCarDao;
import electrikar.database.dao.SqlRentalDao;
import electrikar.database.dao.SqlUserDao;
import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class SqlTest
{
  @Mock
  private SqlCarDao sqlCarDao;
  private SqlUserDao sqlUserDao;
  private SqlRentalDao sqlRentalDao;

  @BeforeEach void setUp()
  {
      sqlCarDao = Mockito.mock(SqlCarDao.class);
     sqlUserDao = Mockito.mock(SqlUserDao.class);
     sqlRentalDao = Mockito.mock(SqlRentalDao.class);
  }

  @Test void testCreateCar() throws SQLException
  {
    var car = new Car("1234", CarColor.valueOf(1), CarMake.valueOf(1),
        CarModel.valueOf(2), CarType.SEDAN, 0, "image", false);
    var newCar = sqlCarDao.createCar(car.getReg_number(), car.getColor(),
        car.getMake(), car.getModel(), car.getType(), car.getPrice(),
        car.getImage(), car.checkRent());
    assert newCar.getReg_number().equals("1234");
    assert newCar.getColor().equals(CarColor.valueOf(1));
    assert newCar.getMake().equals(CarMake.valueOf(1));
    assert newCar.getModel().equals(CarModel.valueOf(2));
    assert newCar.getType().equals(CarType.SEDAN);
    assert newCar.getPrice() == 0;
    assert newCar.getImage().equals("image");
    assert !newCar.checkRent();

  }

  @Test void createRental() throws SQLException
  {
    var rental = new Rental(1, "1234", 1, new Timestamp(01 - 02 - 2025),
        new Timestamp(01 - 03 - 2025));
    var newRental = sqlRentalDao.createRental(rental.getCarRegNumber(),
        rental.getUserId(), rental.getStartDate(), rental.getEndDate());
    assert newRental.getId() == 1;
    assert newRental.getCarRegNumber().equals("1234");
    assert newRental.getUserId() == 1;
    assert newRental.getStartDate().equals(new Timestamp(1));
    assert newRental.getEndDate().equals(new Timestamp(2));
  }

  @Test void createWrongRental() throws SQLException
  {
    var rental = new Rental(2, "1234", 1, new Timestamp(01 - 02 - 2025),
        new Timestamp(01 - 01 - 2025));
    var newRental = sqlRentalDao.createRental(rental.getCarRegNumber(),
        rental.getUserId(), rental.getStartDate(), rental.getEndDate());

    verify(sqlRentalDao, times(1)).createRental(rental.getCarRegNumber(),
        rental.getUserId(), rental.getStartDate(), rental.getEndDate());
    assertThrows(SQLException.class,
        () -> sqlRentalDao.createRental(rental.getCarRegNumber(),
            rental.getUserId(), rental.getStartDate(), rental.getEndDate()));
  }

  // Add more tests here

  @Test void testUpdateCar() throws SQLException
  {
    var car = new Car("1234", CarColor.valueOf(1), CarMake.valueOf(1),
        CarModel.valueOf(2), CarType.SEDAN, 0, "image", false);
    sqlCarDao.updateCar("1234", car);
    verify(sqlCarDao, times(1)).updateCar("1234", car);
  }

  @Test void testDeleteCar() throws SQLException
  {
    sqlCarDao.deleteCarByReg("1234");
    verify(sqlCarDao, times(1)).deleteCarByReg("1234");
  }

  @Test void testCreateRental() throws SQLException, RemoteException {
    var car = new Car("5678", CarColor.valueOf(2), CarMake.valueOf(2),
        CarModel.valueOf(3), CarType.SUV, 100, "image2", false);
    var newCar = sqlCarDao.createCar(car.getReg_number(), car.getColor(),
        car.getMake(), car.getModel(), car.getType(), car.getPrice(),
        car.getImage(), car.checkRent());

    assert newCar.getReg_number().equals("5678");
    assert newCar.getColor().equals(CarColor.valueOf(2));
    assert newCar.getMake().equals(CarMake.valueOf(2));
    assert newCar.getModel().equals(CarModel.valueOf(3));
    assert newCar.getType().equals(CarType.SUV);
    assert newCar.getPrice() == 100;
    assert newCar.getImage().equals("image2");
    assert !newCar.checkRent();

    var rental = new Rental(3, "5678", 2, new Timestamp(System.currentTimeMillis()),
        new Timestamp(System.currentTimeMillis() + 86400000)); // 1 day rental
    var newRental = sqlRentalDao.createRental(rental.getCarRegNumber(),
        rental.getUserId(), rental.getStartDate(), rental.getEndDate());

    assert newRental.getId() == 3;
    assert newRental.getCarRegNumber().equals("5678");
    assert newRental.getUserId() == 2;
    assert newRental.getStartDate().equals(rental.getStartDate());
    assert newRental.getEndDate().equals(rental.getEndDate());}

}
