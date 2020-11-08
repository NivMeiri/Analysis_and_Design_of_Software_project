/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.Date;
import java.util.*;
// line 24 "model.ump"
// line 121 "model.ump"
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
  private float balance;

  //Account Associations
  private Customer customer;
  private List<Payment> payments;
  private ShoppingCart shoppingCart;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aID, String aBilling_Address, boolean aIs_closed, Date aOpen, Date aClosed, float aBalance, Customer aCustomer, ShoppingCart aShoppingCart)
  {
    ID = aID;
    Billing_Address = aBilling_Address;
    is_closed = aIs_closed;
    open = aOpen;
    closed = aClosed;
    balance = aBalance;

    customer = aCustomer;
    payments = new ArrayList<Payment>();

    shoppingCart = aShoppingCart;
    orders = new ArrayList<Order>();
  }

  public Account(String aID, String aBilling_Address, boolean aIs_closed, Date aOpen, Date aClosed, float aBalance, String aIdForCustomer, Address aAddressForCustomer, String aPhoneForCustomer, String aEmailForCustomer, Date aCretaedForShoppingCart, WebUser aWebUserForShoppingCart)
  {
    ID = aID;
    Billing_Address = aBilling_Address;
    is_closed = aIs_closed;
    open = aOpen;
    closed = aClosed;
    balance = aBalance;
    customer = new Customer(aIdForCustomer, aAddressForCustomer, aPhoneForCustomer, aEmailForCustomer, this);
    payments = new ArrayList<Payment>();
    shoppingCart = new ShoppingCart(aCretaedForShoppingCart, aWebUserForShoppingCart, this);
    orders = new ArrayList<Order>();
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

  public void setShoppingCart(ShoppingCart s){
    this.shoppingCart = s;
  }

  public boolean setClosed(Date aClosed)
  {
    boolean wasSet = false;
    closed = aClosed;
    wasSet = true;
    return wasSet;
  }

  public boolean setBalance(float aBalance)
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

  public float getBalance()
  {
    return balance;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetMany */
  public Payment getPayment(int index)
  {
    Payment aPayment = payments.get(index);
    return aPayment;
  }

  public List<Payment> getPayments()
  {
    List<Payment> newPayments = Collections.unmodifiableList(payments);
    return newPayments;
  }

  public int numberOfPayments()
  {
    int number = payments.size();
    return number;
  }

  public boolean hasPayments()
  {
    boolean has = payments.size() > 0;
    return has;
  }

  public int indexOfPayment(Payment aPayment)
  {
    int index = payments.indexOf(aPayment);
    return index;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPayments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Payment addPayment(String aId, Date aPaid, float aTotal, String aDetails, Order aOrder)
  {
    //todo Add user question if the payment is immediate or Delayed
    return new ImmediatePayment(aId, aPaid, aTotal, aDetails, this, aOrder,true);
  }

  public boolean addPayment(Payment aPayment)
  {
    boolean wasAdded = false;
    if (payments.contains(aPayment)) { return false; }
    Account existingAccount = aPayment.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aPayment.setAccount(this);
    }
    else
    {
      payments.add(aPayment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePayment(Payment aPayment)
  {
    boolean wasRemoved = false;
    //Unable to remove aPayment, as it must always have a account
    if (!this.equals(aPayment.getAccount()))
    {
      payments.remove(aPayment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPaymentAt(Payment aPayment, int index)
  {  
    boolean wasAdded = false;
    if(addPayment(aPayment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPayments()) { index = numberOfPayments() - 1; }
      payments.remove(aPayment);
      payments.add(index, aPayment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePaymentAt(Payment aPayment, int index)
  {
    boolean wasAdded = false;
    if(payments.contains(aPayment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPayments()) { index = numberOfPayments() - 1; }
      payments.remove(aPayment);
      payments.add(index, aPayment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPaymentAt(aPayment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(String aNumber, Date aOrederd, Date aShipped, Address aShip_to, OrderStatus aStatus, float aTotal)
  {
    return new Order(aNumber, aOrederd, aShipped, aShip_to, aStatus, aTotal, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Account existingAccount = aOrder.getAccount();
    boolean isNewAccount = existingAccount != null && !this.equals(existingAccount);
    if (isNewAccount)
    {
      aOrder.setAccount(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a account
    if (!this.equals(aOrder.getAccount()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }
  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    //----Deleting from Dicts.
    int myId = System1.AllObjInSys_obj.get(this);
    System1.AllObjInSys_obj.remove(this);
    System1.AllObjInSys_id.remove(myId);
    //------------------------
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
    for(int i=payments.size(); i > 0; i--)
    {
      Payment aPayment = payments.get(i - 1);
      aPayment.delete();
    }

    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
  }
  public String toString()
  {
    String infoAboutObj = "["+
            "id" + ":" + getID()+ ", " +
            "Billing_Address" + ":" + getBilling_Address()+ ", " +
            "is_closed" + ":" + getIs_closed()+ ", " +
            "balance" + ":" + getBalance()+ "]" +
            "\n" + "open" + "=" + open.toString() +
            "\n" + "closed" + "=" + closed.toString();
    String Conn2Obj = "\nCustomer = "+customer.getId() + ",\nShoppingCart = "+shoppingCart.getCretaed();
    String Orders2Obj = "";
    String Pay2Obj = "";
    for(Order order: orders){
      Orders2Obj +="\nOrder: "+order.getNumber();
    }
    for(Payment pay: payments){
      Pay2Obj +="\nPayment: "+pay.getId();
    }
    return infoAboutObj+"\n"+Conn2Obj+"\n"+Orders2Obj+Pay2Obj;
  }
}