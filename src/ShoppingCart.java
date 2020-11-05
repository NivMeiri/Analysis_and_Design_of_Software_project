/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.Date;
import java.util.*;

// line 60 "model.ump"
// line 143 "model.ump"
public class ShoppingCart
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShoppingCart Attributes
  private Date cretaed;

  //ShoppingCart Associations
  private WebUser webUser;
  private Account account;
  private List<LineItem> lineItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingCart(Date aCretaed, WebUser aWebUser, Account account)
  {
    cretaed = aCretaed;
    boolean didAddWebUser = setWebUser(aWebUser);
    if (!didAddWebUser)
    {
      throw new RuntimeException("Unable to create shoppingCart due to webUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if(account != null){
      this.account = account;
    }

    lineItems = new ArrayList<LineItem>();
  }

  public void SetAccount(Account a){
    if(a!=null) {
      this.account = a;
    }
  }
  public ShoppingCart(Date aCretaed, WebUser aWebUser, String aIDForAccount, String aBilling_AddressForAccount, boolean aIs_closedForAccount, Date aOpenForAccount, Date aClosedForAccount, int aBalanceForAccount, Customer aCustomerForAccount)
  {
    cretaed = aCretaed;
    boolean didAddWebUser = setWebUser(aWebUser);
    if (!didAddWebUser)
    {
      throw new RuntimeException("Unable to create shoppingCart due to webUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    account = new Account(aIDForAccount, aBilling_AddressForAccount, aIs_closedForAccount, aOpenForAccount, aClosedForAccount, aBalanceForAccount, aCustomerForAccount, this);
    lineItems = new ArrayList<LineItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCretaed(Date aCretaed)
  {
    boolean wasSet = false;
    cretaed = aCretaed;
    wasSet = true;
    return wasSet;
  }

  public Date getCretaed()
  {
    return cretaed;
  }
  /* Code from template association_GetOne */
  public WebUser getWebUser()
  {
    return webUser;
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
  /* Code from template association_SetOneToOptionalOne */
  public boolean setWebUser(WebUser aNewWebUser)
  {
    boolean wasSet = false;
    if (aNewWebUser == null)
    {
      //Unable to setWebUser to null, as shoppingCart must always be associated to a webUser
      return wasSet;
    }
    
    ShoppingCart existingShoppingCart = aNewWebUser.getShoppingCart();
    if (existingShoppingCart != null && !equals(existingShoppingCart))
    {
      //Unable to setWebUser, the current webUser already has a shoppingCart, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    WebUser anOldWebUser = webUser;
    webUser = aNewWebUser;
    webUser.setShoppingCart(this);

    if (anOldWebUser != null)
    {
      anOldWebUser.setShoppingCart(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLineItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LineItem addLineItem(int aQuantity, int aPrice, Order aOrder, Product aProduct)
  {
    return new LineItem(aQuantity, aPrice, this, aOrder, aProduct);
  }

  public boolean addLineItem(LineItem aLineItem)
  {
    boolean wasAdded = false;
    if (lineItems.contains(aLineItem)) { return false; }
    ShoppingCart existingShoppingCart = aLineItem.getShoppingCart();
    boolean isNewShoppingCart = existingShoppingCart != null && !this.equals(existingShoppingCart);
    if (isNewShoppingCart)
    {
      aLineItem.setShoppingCart(this);
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
    //Unable to remove aLineItem, as it must always have a shoppingCart
    if (!this.equals(aLineItem.getShoppingCart()))
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
    WebUser existingWebUser = webUser;
    webUser = null;
    if (existingWebUser != null)
    {
      existingWebUser.setShoppingCart(null);
    }
    Account existingAccount = account;
    account = null;
    if (existingAccount != null)
    {
      existingAccount.delete();
    }
    for(int i=lineItems.size(); i > 0; i--)
    {
      LineItem aLineItem = lineItems.get(i - 1);
      aLineItem.delete();
    }
  }


  public String toString()
  {
    String InfoAboutObj =
            "Cretaed" + "=" + cretaed.toString()
            + "\nWebUser = "+ webUser.getLogin_id()
            + "\nAccount = "+ account.getID();
    String Line2Obj = "";
    for(LineItem li: lineItems){
      Line2Obj+= "\nLineItem: "+li.getProduct().getName();
    }
    return InfoAboutObj+Line2Obj;
  }
}