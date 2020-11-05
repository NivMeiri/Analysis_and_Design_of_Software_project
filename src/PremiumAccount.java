/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 18 "model.ump"
// line 115 "model.ump"
public class PremiumAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PremiumAccount Associations
  private List<Product> products;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PremiumAccount(String aID, String aBilling_Address, boolean aIs_closed, Date aOpen, Date aClosed, int aBalance, Customer aCustomer, ShoppingCart aShoppingCart)
  {
    super(aID, aBilling_Address, aIs_closed, aOpen, aClosed, aBalance, aCustomer, aShoppingCart);
    products = new ArrayList<Product>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Product getProduct(int index)
  {
    Product aProduct = products.get(index);
    return aProduct;
  }

  public List<Product> getProducts()
  {
    List<Product> newProducts = Collections.unmodifiableList(products);
    return newProducts;
  }

  public int numberOfProducts()
  {
    int number = products.size();
    return number;
  }

  public boolean hasProducts()
  {
    boolean has = products.size() > 0;
    return has;
  }

  public int indexOfProduct(Product aProduct)
  {
    int index = products.indexOf(aProduct);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfProducts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addProduct(Product aProduct)
  {
    boolean wasAdded = false;
    if (products.contains(aProduct)) { return false; }
    PremiumAccount existingPremiumAccount = aProduct.getPremiumAccount();
    if (existingPremiumAccount == null)
    {
      aProduct.setPremiumAccount(this);
    }
    else if (!this.equals(existingPremiumAccount))
    {
      existingPremiumAccount.removeProduct(aProduct);
      addProduct(aProduct);
    }
    else
    {
      products.add(aProduct);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProduct(Product aProduct)
  {
    boolean wasRemoved = false;
    if (products.contains(aProduct))
    {
      products.remove(aProduct);
      aProduct.setPremiumAccount(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addProductAt(Product aProduct, int index)
  {  
    boolean wasAdded = false;
    if(addProduct(aProduct))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProducts()) { index = numberOfProducts() - 1; }
      products.remove(aProduct);
      products.add(index, aProduct);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProductAt(Product aProduct, int index)
  {
    boolean wasAdded = false;
    if(products.contains(aProduct))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProducts()) { index = numberOfProducts() - 1; }
      products.remove(aProduct);
      products.add(index, aProduct);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProductAt(aProduct, index);
    }
    return wasAdded;
  }

  public void printProducts(){
    for(int i=0;i<this.products.size();i++){
      System.out.println(i+")"+products.get(i).getName()+":"+products.get(i).getAmount());
    }
  }

  public void delete()
  {
    while( !products.isEmpty() )
    {
      products.get(0).setPremiumAccount(null);
    }
    super.delete();
  }

  public String toString(){

    String InfoAboutObj = super.toString();
    String Pro2Obj = "";
    for(Product prod: products){
      Pro2Obj += "\nProduct: "+prod.getName();
    }
    return InfoAboutObj+Pro2Obj;
  }
}