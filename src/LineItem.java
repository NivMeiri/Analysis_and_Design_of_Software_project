/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 79 "model.ump"
// line 157 "model.ump"
public class LineItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LineItem Attributes
  private int quantity;
  private int price;

  //LineItem Associations
  private ShoppingCart shoppingCart;
  private Order order;
  private Product product;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LineItem(int aQuantity, int aPrice, ShoppingCart aShoppingCart, Order aOrder, Product aProduct)
  {
    quantity = aQuantity;
    price = aPrice;
    boolean didAddShoppingCart = setShoppingCart(aShoppingCart);
    if (!didAddShoppingCart)
    {
      throw new RuntimeException("Unable to create lineItem due to shoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
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
  /* Code from template association_SetOneToMany */
  public boolean setShoppingCart(ShoppingCart aShoppingCart)
  {
    boolean wasSet = false;
    if (aShoppingCart == null)
    {
      return wasSet;
    }

    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = aShoppingCart;
    if (existingShoppingCart != null && !existingShoppingCart.equals(aShoppingCart))
    {
      existingShoppingCart.removeLineItem(this);
    }
    shoppingCart.addLineItem(this);
    wasSet = true;
    return wasSet;
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
    ShoppingCart placeholderShoppingCart = shoppingCart;
    this.shoppingCart = null;
    if(placeholderShoppingCart != null)
    {
      placeholderShoppingCart.removeLineItem(this);
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
            "  " + "shoppingCart = "+(getShoppingCart()!=null?Integer.toHexString(System.identityHashCode(getShoppingCart())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "product = "+(getProduct()!=null?Integer.toHexString(System.identityHashCode(getProduct())):"null");
  }
}