/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;
import java.util.*;
enum OrderStatus {
  New,
  Hold,
  Shipped,
  Delivered,
  Closed
}
// line 67 "model.ump"
// line 150 "model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private String number;
  private Date orederd;
  private Date shipped;
  private Address ship_to;
  private OrderStatus Status;
  private float total;

  //Order Associations
  private List<Payment> payments;
  private Account account;
  private List<LineItem> lineItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(String aNumber, Date aOrederd, Date aShipped, Address aShip_to, OrderStatus aStatus, float aTotal, Account aAccount)
  {
    number = aNumber;
    orederd = aOrederd;
    shipped = aShipped;
    ship_to = aShip_to;
    Status = aStatus;
    total = aTotal;
    payments = new ArrayList<Payment>();
    boolean didAddAccount = setAccount(aAccount);
    if (!didAddAccount)
    {
      throw new RuntimeException("Unable to create order due to account. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    lineItems = new ArrayList<LineItem>();
  }


  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(String aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrederd(Date aOrederd)
  {
    boolean wasSet = false;
    orederd = aOrederd;
    wasSet = true;
    return wasSet;
  }

  public boolean setShipped(Date aShipped)
  {
    boolean wasSet = false;
    shipped = aShipped;
    wasSet = true;
    return wasSet;
  }

  public boolean setShip_to(Address aShip_to)
  {
    boolean wasSet = false;
    ship_to = aShip_to;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(OrderStatus aStatus)
  {
    boolean wasSet = false;
    Status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotal(float aTotal)
  {
    boolean wasSet = false;
    total = aTotal;
    wasSet = true;
    return wasSet;
  }

  public String getNumber()
  {
    return number;
  }

  public Date getOrederd()
  {
    return orederd;
  }

  public Date getShipped()
  {
    return shipped;
  }

  public Address getShip_to()
  {
    return ship_to;
  }

  public OrderStatus getStatus()
  {
    return Status;
  }

  public float getTotal()
  {
    return total;
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
  public Account getAccount()
  {
    return account;
  }
  /* Code from template association_GetMany */
  public LineItem getLineItem(int index)
  {
    LineItem aLineItem = lineItems.get(index);
    return aLineItem;
  }

  public List<LineItem> getLineItems()
  {
    List<LineItem> newLineItems = Collections.unmodifiableList(lineItems);
    return newLineItems;
  }

  public int numberOfLineItems()
  {
    int number = lineItems.size();
    return number;
  }

  public boolean hasLineItems()
  {
    boolean has = lineItems.size() > 0;
    return has;
  }

  public int indexOfLineItem(LineItem aLineItem)
  {
    int index = lineItems.indexOf(aLineItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPayments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Payment addPayment(String aId, Date aPaid, float aTotal, String aDetails, Account aAccount)
  {
    return new Payment(aId, aPaid, aTotal, aDetails, aAccount, this);
  }

  public boolean addPayment(Payment aPayment)
  {
    boolean wasAdded = false;
    if (payments.contains(aPayment)) { return false; }
    Order existingOrder = aPayment.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aPayment.setOrder(this);
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
    //Unable to remove aPayment, as it must always have a order
    if (!this.equals(aPayment.getOrder()))
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
  /* Code from template association_SetOneToMany */
  public boolean setAccount(Account aAccount)
  {
    boolean wasSet = false;
    if (aAccount == null)
    {
      return wasSet;
    }

    Account existingAccount = account;
    account = aAccount;
    if (existingAccount != null && !existingAccount.equals(aAccount))
    {
      existingAccount.removeOrder(this);
    }
    account.addOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLineItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LineItem addLineItem(int aQuantity, int aPrice, ShoppingCart aShoppingCart, Product aProduct)
  {
    return new LineItem(aQuantity, aPrice, aShoppingCart, this, aProduct);
  }

  public boolean addLineItem(LineItem aLineItem)
  {
    boolean wasAdded = false;
    if (lineItems.contains(aLineItem)) { return false; }
    Order existingOrder = aLineItem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aLineItem.setOrder(this);
    }
    else
    {
      lineItems.add(aLineItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLineItem(LineItem aLineItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aLineItem, as it must always have a order
    if (!this.equals(aLineItem.getOrder()))
    {
      lineItems.remove(aLineItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLineItemAt(LineItem aLineItem, int index)
  {  
    boolean wasAdded = false;
    if(addLineItem(aLineItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLineItems()) { index = numberOfLineItems() - 1; }
      lineItems.remove(aLineItem);
      lineItems.add(index, aLineItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLineItemAt(LineItem aLineItem, int index)
  {
    boolean wasAdded = false;
    if(lineItems.contains(aLineItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLineItems()) { index = numberOfLineItems() - 1; }
      lineItems.remove(aLineItem);
      lineItems.add(index, aLineItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLineItemAt(aLineItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=payments.size(); i > 0; i--)
    {
      Payment aPayment = payments.get(i - 1);
      aPayment.delete();
    }
    Account placeholderAccount = account;
    this.account = null;
    if(placeholderAccount != null)
    {
      placeholderAccount.removeOrder(this);
    }
    for(int i=lineItems.size(); i > 0; i--)
    {
      LineItem aLineItem = lineItems.get(i - 1);
      aLineItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "total" + ":" + getTotal()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orederd" + "=" + (getOrederd() != null ? !getOrederd().equals(this)  ? getOrederd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "shipped" + "=" + (getShipped() != null ? !getShipped().equals(this)  ? getShipped().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "ship_to" + "=" + (getShip_to() != null ? !getShip_to().equals(this)  ? getShip_to().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "Status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "account = "+(getAccount()!=null?Integer.toHexString(System.identityHashCode(getAccount())):"null");
  }
}