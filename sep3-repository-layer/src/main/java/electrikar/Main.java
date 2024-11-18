package electrikar;

import electrikar.database.dao.*;

import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.sql.Timestamp;

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


    SqlCarDao carDao = SqlCarDao.getInstance();
    carDao.createCar("1234", CarColor.valueOf(1), CarMake.valueOf(2), CarModel.valueOf(2), CarType.valueOf(2), 1000, new byte[0]);

    SqlUserDao userDao = SqlUserDao.getInstance();
    userDao.createUser( "John Doe", "john.doe@example.com", "password123", "123-456-7890","32165431", true, false);
    SqlRentalDao rentalDao = SqlRentalDao.getInstance();
    rentalDao.createRental("1234", 1, Timestamp.valueOf("12-02-2004"), Timestamp.valueOf("13-02-2004"));

    System.out.println(rentalDao.getAllRentals());






    System.out.println("Hello World!");
    server.start();
    server.awaitTermination();

    System.out.println("Hello World!");
  }
}

