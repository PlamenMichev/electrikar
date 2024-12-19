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
  private final DatabaseConnector dbConnector = new DatabaseConnector();

  /**
   * Constructor for SqlCarDao
   * @throws SQLException
   */
  public SqlCarDao() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Get instance of SqlCarDao
   * @return
   * @throws SQLException
   */
  public static synchronized SqlCarDao getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new SqlCarDao();
    }
    return instance;
  }

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
  @Override public Car createCar(String regNum, CarColor color,
                                 CarMake make, CarModel model, CarType type, int price, String image, boolean hasRentals) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Car\"(reg_num, color,  make, model, type, price, image) VALUES (?, ?, ?, ?, ?, ?, ?)");
      statement.setString(1, regNum);
      statement.setInt(2, color.ordinal());
      statement.setInt(3, make.ordinal());
      statement.setInt(4, model.ordinal());
      statement.setInt(5, type.ordinal());
      statement.setInt(6, price);
      statement.setString(7, image);
      statement.executeUpdate();
      return new Car(regNum, color, make, model, type, price, image, false);
    }
  }

  /**
   * Update a car in the database
   * @param oldReg
   * @param car
   * @throws SQLException
   */
  @Override public void updateCar(String oldReg, Car car) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"Car\" SET reg_num = ?, color = ?, make = ?, model = ?, type = ?, price = ?, image = ? WHERE reg_num = ?");
      statement.setString(1, car.getReg_number());
      statement.setInt(2, car.getColor().ordinal());
      statement.setInt(3, car.getMake().ordinal());
      statement.setInt(4, car.getModel().ordinal());
      statement.setInt(5, car.getType().ordinal());
      statement.setInt(6, car.getPrice());
      statement.setString(7, car.getImage());
      statement.setString(8, oldReg);
      statement.executeUpdate();
    }
  }

  /**
   * Get a car by its registration number
   * @param reg
   * @return
   * @throws SQLException
   */
  @Override public Car getCarByReg(String reg) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT reg_num, color, make, model, type, price, image,  "
          + "CASE WHEN COUNT(R.car_reg) > 0 THEN TRUE ELSE FALSE END AS has_rentals "
          + "FROM \"Car\" "
          + "LEFT JOIN \"Rental\" R ON R.car_reg = reg_num "
          + "WHERE reg_num = ? "
          + "GROUP BY reg_num, color, make, model, type, price, image");
      statement.setString(1, reg);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        CarColor color = CarColor.valueOf(resultSet.getInt("color"));
        CarModel model = CarModel.valueOf(resultSet.getInt("model"));
        CarMake make = CarMake.valueOf(resultSet.getInt("make"));
        CarType type = CarType.valueOf(resultSet.getInt("type"));
        int price = resultSet.getInt("price");
        String image = resultSet.getString("image");
        boolean hasRentals = resultSet.getBoolean("has_rentals");
        return new Car(reg, color, make, model, type, price, image, hasRentals);
      }
      else {
        return null;
      }
    }
  }

  /**
   * Delete a car by its registration number
   * @param reg
   * @throws SQLException
   */
  @Override public void deleteCarByReg(String reg) throws SQLException
  {
    try(Connection connection = dbConnector.connect())
    {var carDao = SqlCarDao.getInstance();
      if (carDao.getCarByReg(reg).checkRent()) {
        System.err.println("Error: Car has rentals");
      }
      else {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Car\" WHERE reg_num = ?");
      statement.setString(1, reg);
      statement.executeUpdate();}

    }
  }

  /**
   * Get all cars in the database
   * @return
   * @throws SQLException
   */
  @Override
    public ArrayList<Car> getAll() throws SQLException
    {
        try(Connection connection = dbConnector.connect())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT reg_num, color, make, model, type, price, image,  "
                + "CASE WHEN COUNT(R.car_reg) > 0 THEN TRUE ELSE FALSE END AS has_rentals "
                + "FROM \"Car\" "
                + "LEFT JOIN \"Rental\" R ON R.car_reg = reg_num "
                + "GROUP BY reg_num, color, make, model, type, price, image");
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
                String image = resultSet.getString("image");
                boolean hasRentals = resultSet.getBoolean("has_rentals");
                cars.add(new Car(reg, color, make, model, type, price, image, hasRentals));
            }
            return cars;
        }
    }
}
