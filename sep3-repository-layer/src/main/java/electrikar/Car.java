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
  private boolean isRented;

  public Car(String reg_number, CarColor color,  CarMake make, CarModel model,
      CarType type, int price, String image)
  {
    this.reg_number = reg_number;
    this.color = color;
    this.model = model;
    this.make = make;
    this.type = type;
    this.price = price;
    this.image = image;
  }

  public String getReg_number()
  {
    return reg_number;
  }

  public void setReg_number(String reg_number)
  {
    this.reg_number = reg_number;
  }

  public CarColor getColor()
  {
    return color;
  }

  public void setColor(CarColor color)
  {
    this.color = color;
  }

  public CarModel getModel()
  {
    return model;
  }

  public void setModel(CarModel model)
  {
    this.model = model;
  }

  public CarMake getMake()
  {
    return make;
  }

  public void setMake(CarMake make)
  {
    this.make = make;
  }

  public CarType getType()
  {
    return type;
  }

  public void setType(CarType type)
  {
    this.type = type;
  }

  public int getPrice()
  {
    return price;
  }


  public void setPrice(int price)
  {
    this.price = price;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }
  public void setRented(boolean rented)
  {
    isRented = rented;
  }
  public boolean checkRent()
  {
    return isRented;
  }

  public String toString() {
    return "Car{" +
        "regNum='" + reg_number + '\'' +
        ", color=" + color +
        ", make=" + make +
        ", model=" + model +
        ", type=" + type +
        ", price=" + price +
        ", image=" + image +
        '}';
  }
}


