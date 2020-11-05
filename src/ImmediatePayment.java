/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 45 "model.ump"
// line 133 "model.ump"
public class ImmediatePayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ImmeditePayment Attributes
  private boolean phoneConfirmation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ImmediatePayment(String aId, Date aPaid, float aTotal, String aDetails, Account aAccount, Order aOrder, boolean aPhoneConfirmation)
  {
    super(aId, aPaid, aTotal, aDetails, aAccount, aOrder);
    phoneConfirmation = aPhoneConfirmation;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneConfirmation(boolean aPhoneConfirmation)
  {
    boolean wasSet = false;
    phoneConfirmation = aPhoneConfirmation;
    wasSet = true;
    return wasSet;
  }

  public boolean getPhoneConfirmation()
  {
    return phoneConfirmation;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() +
            "\nPhoneConfirmation" + ":" + getPhoneConfirmation();
  }
}