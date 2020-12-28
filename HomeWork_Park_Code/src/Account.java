public class Account {
    int CreditCardNum;
    int limit;
    Double Money;

    public Boolean hasEnoughMoney(int toCheck){
        if(Money-toCheck>0)
            return true;
        else
            return false;
    }
    public void approvePayment(int amount){
        if(hasEnoughMoney(amount))
            this.Money=Money-amount;
    }
}
