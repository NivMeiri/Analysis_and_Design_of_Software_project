/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 95 "model.ump"
// line 170 "model.ump"
public class Supplier
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supplier Attributes
  private String id;
  private String name;

  //Supplier Associations
  private List<Product> products;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supplier(String aId, String aName)
  {
    id = aId;
    name = aName;
    products = new ArrayList<Product>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }
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
  /* Code from template association_AddManyToOne */

  /// todo check what default price to set
  public Product addProduct(String aId, String aName)
  {
    return new Product(aId, aName, this,999);
  }

  public boolean addProduct(Product aProduct)
  {
    boolean wasAdded = false;
    if (products.contains(aProduct)) { return false; }
    Supplier existingSupplier = aProduct.getSupplier();
    boolean isNewSupplier = existingSupplier != null && !this.equals(existingSupplier);
    if (isNewSupplier)
    {
      aProduct.setSupplier(this);
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
    //Unable to remove aProduct, as it must always have a supplier
    if (!this.equals(aProduct.getSupplier()))
    {
      products.remove(aProduct);
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

  public void delete()
  {
    //----Deleting from Dicts.
    int myId = System1.AllObjInSys_obj.get(this);
    System1.AllObjInSys_obj.remove(this);
    System1.AllObjInSys_id.remove(myId);
    //------------------------
    for(int i=products.size(); i > 0; i--)
    {
      Product aProduct = products.get(i - 1);
      aProduct.delete();
    }
  }


  public String toString()
  {
    String InfoAboutObj = "["+
            "id" + ":" + getId()+ ", " +
            "name" + ":" + getName()+ "]";
    String Prod2Obj = "";
    /*
    for(Product prod: products){
      Prod2Obj+="\nProduct: "+prod.getName();
    }
    */

    return InfoAboutObj;
  }
}