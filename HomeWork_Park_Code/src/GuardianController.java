import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuardianController {
    int Pass = 0;
    ArrayList<Guardian> Guradian_list = new ArrayList<Guardian>();
    ChildController childControll;
    public  GuardianController(){
            }

    public void addChild(int age,String name,double weight,double height,int time, Guardian g) {
        Child c=childControll.CreateChild(age, name, weight,height,time);
        g.addChild(c, Pass);
        System.out.println("your child password is: "+Pass);
        System.out.println("your child id is: "+c.ID);
        Pass += 1;
    }

    public void deleteChild(Child c, Guardian g) {
        g.deleteChild(c);
        childControll.deleteChild(c);

    }
    public void deleteGuardian(Guardian g){
        for (Child c: g.childDict.keySet()){
            childControll.deleteChild(c);
        }
        g.childDict=null;
        Guradian_list.remove(g);
        Main.systemObjects.remove(g.a);
        Main.systemObjects.remove(g);
    }

    public void checkCCNum(int ccnum,double money,int limit, Guardian g) throws Exception {
        g.CheckCreditCard(ccnum,money,limit);
    }

    public Guardian createGuardian(String id, int pass) {
        Guardian g = new Guardian();
        g.Password=pass;
        g.id=id;
        this.Guradian_list.add(g);
        Main.systemObjects.add(g);
        return g;
    }
    public Guardian CheckGuardDetails(String id, int pass) {
        for (Guardian g : this.Guradian_list) {
            if (g.id.equals(id)) {
                if (g.Password == pass) {
                    System.out.println("the password is correct!");
                    return g;
                } else {
                    System.out.println("wrong password!");
                    return null;
                }

            }

        }
        System.out.println(" the id is not exist");
        return null;
    }

    public  void AddEntry(Guardian g,Child c,Device d){
        childControll.AddEntry(c,d);
        boolean x=g.a.hasEnoughMoney(100);
        if(x)
            g.a.approvePayment(100);
    }
    public void DeleteEntry(Entry e,Child c){
        childControll.DeleteEntry(c,e);
    }
}
