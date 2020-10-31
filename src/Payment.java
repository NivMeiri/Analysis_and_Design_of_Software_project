/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 50 "model.ump"
public class Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Payment Attributes
  private String Id;
  private Date Paid;
  private float Total;
  private String Details;

  //Payment Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Payment(String aId, Date aPaid, float aTotal, String aDetails, Order aOrder)
  {
    Id = aId;
    Paid = aPaid;
    Total = aTotal;
    Details = aDetails;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create payment due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    Id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaid(Date aPaid)
  {
    boolean wasSet = false;
    Paid = aPaid;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotal(float aTotal)
  {
    boolean wasSet = false;
    Total = aTotal;
    wasSet = true;
    return wasSet;
  }

  public boolean setDetails(String aDetails)
  {
    boolean wasSet = false;
    Details = aDetails;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return Id;
  }

  public Date getPaid()
  {
    return Paid;
  }

  public float getTotal()
  {
    return Total;
  }

  public String getDetails()
  {
    return Details;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
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
      existingOrder.removePayment(this);
    }
    order.addPayment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removePayment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "Id" + ":" + getId()+ "," +
            "Total" + ":" + getTotal()+ "," +
            "Details" + ":" + getDetails()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "Paid" + "=" + (getPaid() != null ? !getPaid().equals(this)  ? getPaid().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}