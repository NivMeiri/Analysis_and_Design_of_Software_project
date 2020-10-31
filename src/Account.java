/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 24 "model.ump"
public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String ID;
  private String Billing_Address;
  private boolean is_closed;
  private Date open;
  private Date closed;
  private int balance;

  //Account Associations
  private Customer customer;
  private ShoppingCart shoppingCart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aID, String aBilling_Address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Customer aCustomer, ShoppingCart aShoppingCart)
  {
    ID = aID;
    Billing_Address = aBilling_Address;
    is_closed = aIs_closed;
    open = aOpen;
    closed = aClosed;
    balance = aBalance;
    if (aCustomer == null || aCustomer.getAccount() != null)
    {
      throw new RuntimeException("Unable to create Account due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customer = aCustomer;
    if (aShoppingCart == null || aShoppingCart.getAccount() != null)
    {
      throw new RuntimeException("Unable to create Account due to aShoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shoppingCart = aShoppingCart;
  }

  public Account(String aID, String aBilling_Address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, String aIdForCustomer, Address aAddressForCustomer, String aPhoneForCustomer, String aEmailForCustomer, Date aCretaedForShoppingCart, WebUser aWebUserForShoppingCart)
  {
    ID = aID;
    Billing_Address = aBilling_Address;
    is_closed = aIs_closed;
    open = aOpen;
    closed = aClosed;
    balance = aBalance;
    customer = new Customer(aIdForCustomer, aAddressForCustomer, aPhoneForCustomer, aEmailForCustomer, this);
    shoppingCart = new ShoppingCart(aCretaedForShoppingCart, aWebUserForShoppingCart, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setID(String aID)
  {
    boolean wasSet = false;
    ID = aID;
    wasSet = true;
    return wasSet;
  }

  public boolean setBilling_Address(String aBilling_Address)
  {
    boolean wasSet = false;
    Billing_Address = aBilling_Address;
    wasSet = true;
    return wasSet;
  }

  public boolean setIs_closed(boolean aIs_closed)
  {
    boolean wasSet = false;
    is_closed = aIs_closed;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpen(Date aOpen)
  {
    boolean wasSet = false;
    open = aOpen;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosed(Date aClosed)
  {
    boolean wasSet = false;
    closed = aClosed;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(int aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public String getID()
  {
    return ID;
  }

  public String getBilling_Address()
  {
    return Billing_Address;
  }

  public boolean getIs_closed()
  {
    return is_closed;
  }

  public Date getOpen()
  {
    return open;
  }

  public Date getClosed()
  {
    return closed;
  }

  public int getBalance()
  {
    return balance;
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

  public void delete()
  {
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = null;
    if (existingShoppingCart != null)
    {
      existingShoppingCart.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "ID" + ":" + getID()+ "," +
            "Billing_Address" + ":" + getBilling_Address()+ "," +
            "is_closed" + ":" + getIs_closed()+ "," +
            "balance" + ":" + getBalance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "open" + "=" + (getOpen() != null ? !getOpen().equals(this)  ? getOpen().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "closed" + "=" + (getClosed() != null ? !getClosed().equals(this)  ? getClosed().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shoppingCart = "+(getShoppingCart()!=null?Integer.toHexString(System.identityHashCode(getShoppingCart())):"null");
  }
}