import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Guardian {
    HashMap<Child,Integer> childDict=new HashMap<Child,Integer>();
    Account a;
    String id;
    int Password;
    public void approveExtreme(ArrayList<Device> Device_arr) {
        System.out.println("This is the list of the extreme device that you choose:");
        for (Device d : Device_arr) {
            System.out.println(d.name);
        }
    }
    public void addChild(Child c,int pass){
        childDict.put(c,pass);
        System.out.println("Child added successfully");
        }
    public void deleteChild(Child c){
        childDict.remove(c);
    }
    public void  CheckCreditCard(int c,double money, int limit) throws Exception{
        TimeUnit.SECONDS.sleep(2);
        System.out.println("the Credit-card checked and its fine");
        createAccount(c,money,limit);

    }
    public void createAccount(int ccnum,double money, int limit){
        this.a=new Account();
        this.a.CreditCardNum=ccnum;
        this.a.Money=money;
        this.a.limit=limit;
        Main.systemObjects.add(this.a);
    }
    public Child findChild(String name){
        for (Child c: this.childDict.keySet()){
            if (c.name.equals(name)){
                return c;
            }
        }
        return null;
    }
    public int getTotal(){
        return this.a.getTotal();
    }
}
