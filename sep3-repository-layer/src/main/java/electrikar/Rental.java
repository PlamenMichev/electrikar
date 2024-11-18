package electrikar;

import electrikar.enums.RentalStatus;

import java.sql.Timestamp;

public class Rental
{
  private long id;
  private String carRegNumber;
  private int userId;
  private Timestamp startDate;
  private Timestamp endDate;
  private Timestamp dropDate;
  private RentalStatus status;
  public String customerComment;
  public String organizerComment;


  public Rental(long id, String carRegNumber, int userId, Timestamp startDate, Timestamp endDate, Timestamp dropDate, RentalStatus status, String customerComment, String organizerComment)
  {
    this.id = id;
    this.carRegNumber = carRegNumber;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.dropDate = dropDate;
    this.status = status;
    this.customerComment = customerComment;
    this.organizerComment = organizerComment;

  }

  public Rental(long id, String carRegNumber, int userId, Timestamp startDate, Timestamp endDate)
  {
    this.id = id;
    this.carRegNumber = carRegNumber;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
    dropDate = null;
    status = RentalStatus.PENDING;
    customerComment = null;
    organizerComment = null;
  }

  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public String getCarRegNumber()
  {
    return carRegNumber;
  }

  public void setCarRegNumber(String carRegNumber)
  {
    this.carRegNumber = carRegNumber;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public Timestamp getStartDate()
  {
    return startDate;
  }
  public long getStartDateLong()
  {
    return startDate.getTime();
  }


  public void setStartDate(Timestamp startDate)
  {
    this.startDate = startDate;
  }

  public Timestamp getEndDate()
  {
    return endDate;
  }

  public long getEndDateLong()
  {
    return endDate.getTime();
  }

  public void setEndDate(Timestamp endDate)
  {
    this.endDate = endDate;
  }

  public Timestamp getDropDate()
  {
    return dropDate;
  }

  public long getDropDateLong()
  {
    return dropDate.getTime();
  }

  public void setDropDate(Timestamp dropDate)
  {
    this.dropDate = dropDate;
  }

  public RentalStatus getStatus()
  {
    return status;
  }

  public void setStatus(RentalStatus status)
  {
    this.status = status;
  }

  public String getCustomerComment()
  {
    return customerComment;
  }

  public void setCustomerComment(String customerComment)
  {
    this.customerComment = customerComment;
  }

  public String getOrganizerComment()
  {
    return organizerComment;
  }

  public void setOrganizerComment(String organizerComment)
  {
    this.organizerComment = organizerComment;
  }



}
