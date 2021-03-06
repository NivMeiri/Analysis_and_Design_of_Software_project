/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

enum UserState {
  New,
  Active,
  Blocked,
  Banned
}
// line 2 "model.ump"
// line 104 "model.ump"
public class WebUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WebUser Attributes
  private String Login_id;
  private String Password;
  private UserState State;

  //WebUser Associations
  private Customer customer;
  private ShoppingCart shoppingCart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WebUser(String aLogin_id, String aPassword, UserState aState, Customer aCustomer)
  {
    Login_id = aLogin_id;
    Password = aPassword;
    State = aState;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create webUser due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLogin_id(String aLogin_id)
  {
    boolean wasSet = false;
    Login_id = aLogin_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    Password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setState(UserState aState)
  {
    boolean wasSet = false;
    State = aState;
    wasSet = true;
    return wasSet;
  }

  public String getLogin_id()
  {
    return Login_id;
  }

  public String getPassword()
  {
    return Password;
  }

  public UserState getState()
  {
    return State;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }

  public boolean hasShoppingCart()
  {
    boolean has = shoppingCart != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer == null)
    {
      //Unable to setCustomer to null, as webUser must always be associated to a customer
      return wasSet;
    }
    
    WebUser existingWebUser = aNewCustomer.getWebUser();
    if (existingWebUser != null && !equals(existingWebUser))
    {
      //Unable to setCustomer, the current customer already has a webUser, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Customer anOldCustomer = customer;
    customer = aNewCustomer;
    customer.setWebUser(this);

    if (anOldCustomer != null)
    {
      anOldCustomer.setWebUser(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setShoppingCart(ShoppingCart aNewShoppingCart)
  {
    boolean wasSet = false;
    if (shoppingCart != null && !shoppingCart.equals(aNewShoppingCart) && equals(shoppingCart.getWebUser()))
    {
      //Unable to setShoppingCart, as existing shoppingCart would become an orphan
      return wasSet;
    }

    shoppingCart = aNewShoppingCart;
    WebUser anOldWebUser = aNewShoppingCart != null ? aNewShoppingCart.getWebUser() : null;

    if (!this.equals(anOldWebUser))
    {
      if (anOldWebUser != null)
      {
        anOldWebUser.shoppingCart = null;
      }
      if (shoppingCart != null)
      {
        shoppingCart.setWebUser(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    //----Deleting from Dicts.
    int myId = System1.AllObjInSys_obj.get(this);
    System1.AllObjInSys_obj.remove(this);
    System1.AllObjInSys_id.remove(myId);
    //------------------------
    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = null;
    if (existingShoppingCart != null)
    {
      existingShoppingCart.delete();
    }



    customer = null;


  }


  public String toString()
  {
    String InfoAboutObj = "["+
            "Login_id" + ":" + getLogin_id()+ "," +
            "Password" + ":" + getPassword()+ "]";
    String Conn2Obj =
            "\nState" + "=" + State.toString()
            + "\nCustomer = " + customer.getId()
            + "\nShoppingCart = " +shoppingCart.getCretaed();
    return InfoAboutObj+Conn2Obj;
  }
}