package electrikar;

public class User
{
  private int id;
  private String legalName;
  private String email;
  private String password;
  private String cpr;
  private String phone;
  private boolean isAdmin;
  private boolean isBanned;

  /**
   * Constructor for User
   * @param id
   * @param legalName
   * @param email
   * @param password
   * @param cpr
   * @param phone
   * @param isAdmin
   * @param isBanned
   */
  public User(int id, String legalName, String email, String password, String cpr, String phone, boolean isAdmin, boolean isBanned)
  {
    this.id = id;
    this.legalName = legalName;
    this.email = email;
    this.password = password;
    this.cpr = cpr;
    this.phone = phone;
    this.isAdmin = isAdmin;
    this.isBanned = isBanned;
  }

  /**
   * Getter for the id of the user
   * @return
   */
  public int getId()
  {
    return id;
  }

  /**
   * Setter for the id of the user
   * @param id
   */
  public void setId(int id)
  {
    this.id = id;
  }

  /**
   * Getter for the legal name of the user
   * @return
   */
  public String getLegalName()
  {
    return legalName;
  }

  /**
   * Setter for the legal name of the user
   * @param legalName
   */
  public void setLegalName(String legalName)
  {
    this.legalName = legalName;
  }

  /**
   * Getter for the email of the user
   * @return
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * Setter for the email of the user
   * @param email
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * Getter for the password of the user
   * @return
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Setter for the password of the user
   * @param password
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * Getter for the cpr of the user
   * @return
   */
  public String getCpr()
  {
    return cpr;
  }

  /**
   * Setter for the cpr of the user
   * @param cpr
   */
  public void setCpr(String cpr)
  {
    this.cpr = cpr;
  }

  /**
   * Getter for the phone of the user
   * @return
   */
  public String getPhone()
  {
    return phone;
  }

  /**
   * Setter for the phone of the user
   * @param phone
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  /**
   * Verifier for the admin status of the user
   * @return
   */
  public boolean verifyAdmin()
  {
    return isAdmin;
  }

  /**
   * Veriifier for the banned status of the user
   * @return
   */
  public boolean verifyBanned()
  {
    return isBanned;
  }

  /**
   * Method for banning a user
   * @param isBanned
   */
  public void banUser(boolean isBanned)
  {
    this.isBanned = isBanned;
  }

  /**
   * toString method to return the User object as a string
   * @return
   */
  public String toString()
  {
    return "User{" +
        "id=" + id +
        ", legalName='" + legalName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", cpr='" + cpr + '\'' +
        ", phone='" + phone + '\'' +
        ", isAdmin=" + isAdmin +
        ", isBanned=" + isBanned +
        '}';
  }
}
