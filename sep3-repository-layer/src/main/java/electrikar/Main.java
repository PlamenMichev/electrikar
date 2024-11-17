package electrikar;

import electrikar.database.dao.CarDao;
import electrikar.database.dao.SqlCarDao;
import electrikar.database.dao.SqlUserDao;
import electrikar.database.dao.UserDao;
import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class Main
{

  public static void main(String[] args) throws IOException, InterruptedException, SQLException
  {
    Server server = ServerBuilder
            .forPort(8080)
            .addService(new CarsServiceImpl()).build();

    Car car = new Car("1234",
            CarColor.valueOf(1),
            CarMake.valueOf(2),
            CarModel.valueOf(2),
            CarType.valueOf(2),
            1000,
            "https://billeder.bilbasen.dk/bilbasen/cf2fe26b-cabc-448f-a9f5-538dfce26feb.jpeg?class=S960X960");

    SqlCarDao carDao = SqlCarDao.getInstance();
    carDao.createCar(car.getReg_number(), car.getColor(), car.getMake(), car.getModel(), car.getType(), car.getPrice(), car.getImage());

    System.out.println(carDao.getCarByReg("1234"));

   SqlUserDao userDao = SqlUserDao.getInstance();
    // Insert dummy user data into the database

    System.out.println(userDao.getUserByLegalName("John Doe"));

    System.out.println("Hello World!");
    server.start();
    server.awaitTermination();

    System.out.println("Hello World!");
  }
}

