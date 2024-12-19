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

  /**
   * Constructor for Rental
   * @param id
   * @param carRegNumber
   * @param userId
   * @param startDate
   * @param endDate
   * @param dropDate
   * @param status
   * @param customerComment
   * @param organizerComment
   */
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

  /**
   * Constructor for Rental
   * @param id
   * @param carRegNumber
   * @param userId
   * @param startDate
   * @param endDate
   */
  public Rental(long id, String carRegNumber, int userId, Timestamp startDate, Timestamp endDate)
  {
    this.id = id;
    this.carRegNumber = carRegNumber;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = endDate;
    dropDate = endDate;
    status = RentalStatus.valueOf(0);
    customerComment = null;
    organizerComment = null;
  }

  /**
   * Getter for the id of the rental
   * @return
   */
  public long getId()
  {
    return id;
  }

  /**
   * Setter for the id of the rental
   * @param id
   */
  public void setId(long id)
  {
    this.id = id;
  }

  /**
   * Getter for the registration number of the car
   * @return
   */
  public String getCarRegNumber()
  {
    return carRegNumber;
  }

  /**
   * Setter for the registration number of the car
   * @param carRegNumber
   */
  public void setCarRegNumber(String carRegNumber)
  {
    this.carRegNumber = carRegNumber;
  }

  /**
   * Getter for the user id of the rental
   * @return
   */
  public int getUserId()
  {
    return userId;
  }

  /**
   * Setter for the user id of the rental
   * @param userId
   */
  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  /**
   * Getter for the start date of the rental
   * @return
   */
  public Timestamp getStartDate()
  {
    return startDate;
  }

  /**
   * Getter for the start date of the rental(long format)
   * @return
   */
  public long getStartDateLong()
  {
    return startDate.getTime();
  }

/**
   * Setter for the start date of the rental
   * @param startDate
   */
  public void setStartDate(Timestamp startDate)
  {
    this.startDate = startDate;
  }

  /**
   * Getter for the end date of the rental
   * @return
   */
  public Timestamp getEndDate()
  {
    return endDate;
  }

  /**
   * Getter for the end date of the rental(long format)
   * @return
   */
  public long getEndDateLong()
  {
    return endDate.getTime();
  }

  /**
   * Setter for the end date of the rental
   * @param endDate
   */
  public void setEndDate(Timestamp endDate)
  {
    this.endDate = endDate;
  }

  /**
   * Getter for the drop date of the rental
   * @return
   */
  public Timestamp getDropDate()
  {
    return dropDate;
  }

  /**
   * Getter for the drop date of the rental(long format)
   * @return
   */
  public long getDropDateLong()
  {
    return dropDate.getTime();
  }

  /**
   * Setter for the drop date of the rental
   * @param dropDate
   */
  public void setDropDate(Timestamp dropDate)
  {
    this.dropDate = dropDate;
  }

  /**
   * Getter for the status of the rental
   * @return
   */
  public RentalStatus getStatus()
  {
    return status;
  }

  /**
   * Setter for the status of the rental
   * @param status
   */
  public void setStatus(RentalStatus status)
  {
    this.status = status;
  }

  /**
   * Getter for the customer comment of the rental
   * @return
   */
  public String getCustomerComment()
  {
    return customerComment;
  }

  /**
   * Setter for the customer comment of the rental
   * @param customerComment
   */
  public void setCustomerComment(String customerComment)
  {
    this.customerComment = customerComment;
  }

  /**
   * Getter for the organizer comment of the rental
   * @return
   */
  public String getOrganizerComment()
  {
    return organizerComment;
  }

  /**
   * Setter for the organizer comment of the rental
   * @param organizerComment
   */
  public void setOrganizerComment(String organizerComment)
  {
    this.organizerComment = organizerComment;
  }



}
