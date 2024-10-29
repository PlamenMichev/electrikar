package electrikar.database.dao;

import electrikar.Car;
import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;

import java.sql.*;
import java.util.ArrayList;

public class SqlCarDao implements CarDao
{

  private static SqlCarDao instance;

  public SqlCarDao() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized SqlCarDao getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new SqlCarDao();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=electrikar", "postgres", "Plamen123");
  }
  @Override public Car createCar(String regNum, CarColor color,
                                 CarMake make, CarModel model, CarType type, int price, byte[] image) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Car\"(reg_num, color,  make, model, type, price, image) VALUES (?, ?, ?, ?, ?, ?, ?)");
      statement.setString(1, regNum);
      statement.setInt(2, color.ordinal());
      statement.setInt(3, make.ordinal());
      statement.setInt(4, model.ordinal());
      statement.setInt(5, type.ordinal());
      statement.setInt(6, price);
      statement.setBytes(7, image);
      statement.executeUpdate();
      return new Car(regNum, color, make, model, type, price, image);
    }
  }

  @Override public void updateCar(Car car) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"Car\" SET color = ?, make = ?, model = ?, type = ?, price = ?, image = ? WHERE reg_num = ?");
      statement.setInt(1, car.getColor().ordinal());
      statement.setInt(2, car.getMake().ordinal());
      statement.setInt(3, car.getModel().ordinal());
      statement.setInt(4, car.getType().ordinal());
      statement.setInt(5, car.getPrice());
      statement.setBytes(6, car.getImage());
      statement.setString(7, car.getReg_number());
      statement.executeUpdate();
    }
  }

  @Override public Car getCarByReg(String reg) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Car\" WHERE reg_num = ?");
      statement.setString(1, reg);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        CarColor color = CarColor.valueOf(resultSet.getInt("color"));
        CarModel model = CarModel.valueOf(resultSet.getInt("model"));
        CarMake make = CarMake.valueOf(resultSet.getInt("make"));
        CarType type = CarType.valueOf(resultSet.getInt("type"));
        int price = resultSet.getInt("price");
        byte[] image = resultSet.getBytes("image");
        return new Car(reg, color, make, model, type, price, image);
      }
      else {
        return null;
      }
    }
  }

  @Override public void deleteCarByReg(String reg) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Car\" WHERE reg_num = ?");
      statement.setString(1, reg);
      statement.executeUpdate();
    }
  }

  @Override
    public ArrayList<Car> getAll() throws SQLException
    {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Car\"");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while(resultSet.next())
            {
                String reg = resultSet.getString("reg_num");
                CarColor color = CarColor.valueOf(resultSet.getInt("color"));
                CarModel model = CarModel.valueOf(resultSet.getInt("model"));
                CarMake make = CarMake.valueOf(resultSet.getInt("make"));
                CarType type = CarType.valueOf(resultSet.getInt("type"));
                int price = resultSet.getInt("price");
                byte[] image = resultSet.getBytes("image");
                cars.add(new Car(reg, color, make, model, type, price, image));
            }
            return cars;
        }
    }
}
