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


    System.out.println("Hello World!");
    server.start();
    server.awaitTermination();

    System.out.println("Hello World!");
  }
}

