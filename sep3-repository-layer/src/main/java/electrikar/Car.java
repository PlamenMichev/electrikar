package electrikar;

import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;

public class Car
{
  private String reg_number;
  private CarColor color;
  private CarModel model;
  private CarMake make;
  private CarType type;
  private int price;
  private byte[] image;

  public Car(String reg_number, CarColor color,  CarMake make, CarModel model,
      CarType type, int price, byte[] image)
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

  public byte[] getImage()
  {
    return image;
  }

  public void setImage(byte[] image)
  {
    this.image = image;
  }
}


