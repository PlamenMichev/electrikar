package electrikar;

import electrikar.database.dao.CarDao;
import electrikar.database.dao.SqlCarDao;
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

    System.out.println("Hello World!");
    server.start();
    server.awaitTermination();

    System.out.println("Hello World!");
  }
}

