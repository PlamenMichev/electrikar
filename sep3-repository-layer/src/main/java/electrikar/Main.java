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
            .addService(new CarsServiceImpl())
            .addService(new RentalsServiceImpl())
            .addService(new UsersServiceImpl())
            .build();

    var carDao = SqlCarDao.getInstance();

    System.out.println(carDao.getCarByReg("1234ABCD"));
    System.out.println(carDao.getAll());
carDao.updateCar("1234ABDD", new Car("1234ABdd", CarColor.Red, CarMake.Ford, CarModel.Ford_Kuga, CarType.SEDAN, 0, "image", false));

    System.out.println(carDao.getCarByReg("1234ABDD"));
    System.out.println(carDao.getCarByReg("1234ABdd"));

    System.out.println("Hello World!");
    server.start();
    server.awaitTermination();

    System.out.println("Hello World!");
  }
}

