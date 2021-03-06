/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import javax.sound.sampled.Line;
import java.sql.Date;

// line 9 "model.ump"
// line 109 "model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String Id;
  private Address Address;
  private String phone;
  private String email;

  //Customer Associations
  private WebUser webUser;
  private Account account;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aId, Address aAddress, String aPhone, String aEmail, Account aAccount)
  {
    Id = aId;
    Address = aAddress;
    phone = aPhone;
    email = aEmail;

    account = aAccount;
  }

  public Customer(String aId, Address aAddress, String aPhone, String aEmail, String aIDForAccount, String aBilling_AddressForAccount, boolean aIs_closedForAccount, Date aOpenForAccount, Date aClosedForAccount, int aBalanceForAccount, ShoppingCart aShoppingCartForAccount)
  {
    Id = aId;
    Address = aAddress;
    phone = aPhone;
    email = aEmail;
    account = new Account(aIDForAccount, aBilling_AddressForAccount, aIs_closedForAccount, aOpenForAccount, aClosedForAccount, aBalanceForAccount, this, aShoppingCartForAccount);
  }

  //------------------------
  // INTERFACE
  //------------------------
  public void SetAccount(Account myAccount){
    if(myAccount!=null){
      this.account=myAccount;
    }

  }
  public boolean setId(String aId)
  {
    boolean wasSet = false;
    Id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(Address aAddress)
  {
    boolean wasSet = false;
    Address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(String aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return Id;
  }

  public Address getAddress()
  {
    return Address;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetOne */
  public WebUser getWebUser()
  {
    return webUser;
  }

  public boolean hasWebUser()
  {
    boolean has = webUser != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setWebUser(WebUser aNewWebUser)
  {
    boolean wasSet = false;
    if (webUser != null && !webUser.equals(aNewWebUser) && equals(webUser.getCustomer()))
    {
      //Unable to setWebUser, as existing webUser would become an orphan
      return wasSet;
    }

    webUser = aNewWebUser;
    Customer anOldCustomer = aNewWebUser != null ? aNewWebUser.getCustomer() : null;

    if (!this.equals(anOldCustomer))
    {
      if (anOldCustomer != null)
      {
        anOldCustomer.webUser = null;
      }
      if (webUser != null)
      {
        webUser.setCustomer(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    WebUser existingWebUser = webUser;
    webUser = null;
    //----Deleting from Dicts.
      int myId = System1.AllObjInSys_obj.get(this);
      System1.AllObjInSys_obj.remove(this);
      System1.AllObjInSys_id.remove(myId);

      myId = System1.AllObjInSys_obj.get(Address);
      System1.AllObjInSys_obj.remove(Address);
      System1.AllObjInSys_id.remove(myId);
    //------------------------


  }


  public String toString()
  {
    String InfoAboutObj = "["+
            "Id" + ":" + getId()+ ", " +
            "phone" + ":" + getPhone()+ ", " +
            "email" + ":" + getEmail()+ "]";
    String Conn2Obj =
            "\nAddress" + "=" + Address.toString()+
            "\nWebUser = "+ webUser.getLogin_id()+
            "\nAccount = " + account.getID();
    return InfoAboutObj+Conn2Obj;
  }
}