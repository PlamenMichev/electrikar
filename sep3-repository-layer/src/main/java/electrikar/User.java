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

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getLegalName()
  {
    return legalName;
  }

  public void setLegalName(String legalName)
  {
    this.legalName = legalName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getCpr()
  {
    return cpr;
  }

  public void setCpr(String cpr)
  {
    this.cpr = cpr;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public boolean verifyAdmin()
  {
    return isAdmin;
  }

  public boolean verifyBanned()
  {
    return isBanned;
  }

  public void banUser(boolean isBanned)
  {
    this.isBanned = isBanned;
  }
}
