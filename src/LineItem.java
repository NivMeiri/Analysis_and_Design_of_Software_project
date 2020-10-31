/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 77 "model.ump"
public class LineItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LineItem Attributes
  private int quantity;
  private int price;

  //LineItem Associations
  private List<ShoppingCart> shoppingCarts;
  private Order order;
  private Product product;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LineItem(int aQuantity, int aPrice, Order aOrder, Product aProduct)
  {
    quantity = aQuantity;
    price = aPrice;
    shoppingCarts = new ArrayList<ShoppingCart>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create lineItem due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddProduct = setProduct(aProduct);
    if (!didAddProduct)
    {
      throw new RuntimeException("Unable to create lineItem due to product. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public ShoppingCart getShoppingCart(int index)
  {
    ShoppingCart aShoppingCart = shoppingCarts.get(index);
    return aShoppingCart;
  }

  public List<ShoppingCart> getShoppingCarts()
  {
    List<ShoppingCart> newShoppingCarts = Collections.unmodifiableList(shoppingCarts);
    return newShoppingCarts;
  }

  public int numberOfShoppingCarts()
  {
    int number = shoppingCarts.size();
    return number;
  }

  public boolean hasShoppingCarts()
  {
    boolean has = shoppingCarts.size() > 0;
    return has;
  }

  public int indexOfShoppingCart(ShoppingCart aShoppingCart)
  {
    int index = shoppingCarts.indexOf(aShoppingCart);
    return index;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Product getProduct()
  {
    return product;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShoppingCarts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addShoppingCart(ShoppingCart aShoppingCart)
  {
    boolean wasAdded = false;
    if (shoppingCarts.contains(aShoppingCart)) { return false; }
    shoppingCarts.add(aShoppingCart);
    if (aShoppingCart.indexOfLineItem(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aShoppingCart.addLineItem(this);
      if (!wasAdded)
      {
        shoppingCarts.remove(aShoppingCart);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeShoppingCart(ShoppingCart aShoppingCart)
  {
    boolean wasRemoved = false;
    if (!shoppingCarts.contains(aShoppingCart))
    {
      return wasRemoved;
    }

    int oldIndex = shoppingCarts.indexOf(aShoppingCart);
    shoppingCarts.remove(oldIndex);
    if (aShoppingCart.indexOfLineItem(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aShoppingCart.removeLineItem(this);
      if (!wasRemoved)
      {
        shoppingCarts.add(oldIndex,aShoppingCart);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShoppingCartAt(ShoppingCart aShoppingCart, int index)
  {  
    boolean wasAdded = false;
    if(addShoppingCart(aShoppingCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShoppingCarts()) { index = numberOfShoppingCarts() - 1; }
      shoppingCarts.remove(aShoppingCart);
      shoppingCarts.add(index, aShoppingCart);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShoppingCartAt(ShoppingCart aShoppingCart, int index)
  {
    boolean wasAdded = false;
    if(shoppingCarts.contains(aShoppingCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShoppingCarts()) { index = numberOfShoppingCarts() - 1; }
      shoppingCarts.remove(aShoppingCart);
      shoppingCarts.add(index, aShoppingCart);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShoppingCartAt(aShoppingCart, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeLineItem(this);
    }
    order.addLineItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setProduct(Product aProduct)
  {
    boolean wasSet = false;
    if (aProduct == null)
    {
      return wasSet;
    }

    Product existingProduct = product;
    product = aProduct;
    if (existingProduct != null && !existingProduct.equals(aProduct))
    {
      existingProduct.removeLineItem(this);
    }
    product.addLineItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<ShoppingCart> copyOfShoppingCarts = new ArrayList<ShoppingCart>(shoppingCarts);
    shoppingCarts.clear();
    for(ShoppingCart aShoppingCart : copyOfShoppingCarts)
    {
      aShoppingCart.removeLineItem(this);
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeLineItem(this);
    }
    Product placeholderProduct = product;
    this.product = null;
    if(placeholderProduct != null)
    {
      placeholderProduct.removeLineItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "product = "+(getProduct()!=null?Integer.toHexString(System.identityHashCode(getProduct())):"null");
  }
}