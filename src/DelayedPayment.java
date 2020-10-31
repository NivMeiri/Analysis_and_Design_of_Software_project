/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 38 "model.ump"
public class DelayedPayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DelayedPayment Attributes
  private Date PaymentDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DelayedPayment(String aId, Date aPaid, float aTotal, String aDetails, Order aOrder, Date aPaymentDate)
  {
    super(aId, aPaid, aTotal, aDetails, aOrder);
    PaymentDate = aPaymentDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaymentDate(Date aPaymentDate)
  {
    boolean wasSet = false;
    PaymentDate = aPaymentDate;
    wasSet = true;
    return wasSet;
  }

  public Date getPaymentDate()
  {
    return PaymentDate;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "PaymentDate" + "=" + (getPaymentDate() != null ? !getPaymentDate().equals(this)  ? getPaymentDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}