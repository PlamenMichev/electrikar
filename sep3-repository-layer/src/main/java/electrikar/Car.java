package electrikar;

import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;

import java.util.Arrays;

public class Car
{
  private String reg_number;
  private CarColor color;
  private CarModel model;
  private CarMake make;
  private CarType type;
  private int price;
  private String image;
  private boolean hasRentals;

  /**
   * Constructor for Car
   * @param reg_number
   * @param color
   * @param make
   * @param model
   * @param type
   * @param price
   * @param image
   * @param hasRentals
   */
  public Car(String reg_number, CarColor color,  CarMake make, CarModel model,
      CarType type, int price, String image, boolean hasRentals)
  {
    this.reg_number = reg_number;
    this.color = color;
    this.model = model;
    this.make = make;
    this.type = type;
    this.price = price;
    this.image = image;
    this.hasRentals = hasRentals;
  }

  /**
   * Gettter for the registration number of the car
   * @return
   */
  public String getReg_number()
  {
    return reg_number;
  }

  /**
   * Setter for the registration number of the car
   * @param reg_number
   */
  public void setReg_number(String reg_number)
  {
    this.reg_number = reg_number;
  }

  /**
   * Getter for the color of the car
   * @return
   */
  public CarColor getColor()
  {
    return color;
  }

  /**
   * Setter for the color of the car
   * @param color
   */
  public void setColor(CarColor color)
  {
    this.color = color;
  }


  /**
   * Getter for the model of the car
   * @return
   */
  public CarModel getModel()
  {
    return model;
  }

  /**
   * Setter for the model of the car
   * @param model
   */
  public void setModel(CarModel model)
  {
    this.model = model;
  }

  /**
   * Getter for the make of the car
   * @return
   */
  public CarMake getMake()
  {
    return make;
  }

  /**
   * Setter for the make of the car
   * @param make
   */
  public void setMake(CarMake make)
  {
    this.make = make;
  }

  /**
   * Getter for the type of the car
   * @return
   */
  public CarType getType()
  {
    return type;
  }

  /**
   * Setter for the type of the car
   * @param type
   */
  public void setType(CarType type)
  {
    this.type = type;
  }

  /**
   * Getter for the price of the car
   * @return
   */
  public int getPrice()
  {
    return price;
  }

/**
   * Setter for the price of the car
   * @param price
   */
  public void setPrice(int price)
  {
    this.price = price;
  }

  /**
   * Getter for the image of the car
   * @return
   */
  public String getImage()
  {
    return image;
  }

  /**
   * Setter for the image of the car
   * @param image
   */
  public void setImage(String image)
  {
    this.image = image;
  }



  /**
   * Getter for the Rentals of the car
   * @return
   */
  public boolean checkRent()
  {
    return hasRentals;
  }

  /**
   * toString method to return the Car object as a string
   * @return
   */
  public String toString() {
    return "Car{" +
        "regNum='" + reg_number + '\'' +
        ", color=" + color +
        ", make=" + make +
        ", model=" + model +
        ", type=" + type +
        ", price=" + price +
        ", image=" + image +
        ", hasRentals=" + hasRentals +
        '}';
  }
}


