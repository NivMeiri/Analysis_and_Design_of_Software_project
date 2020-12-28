import java.util.ArrayList;
import java.util.Date;

public class ChildController {
    int id=1;
    ArrayList<Child>  Child_list = new ArrayList<Child>();
    public Child  CreateChild(int age,String name,double weight,double height, int time){
        Child c=new Child(time=1000);
        c.ID=id;
        id+=1;
        c.age=age;
        c.Weight=weight;
        c.Height=height;
        c.name=name;

        Child_list.add(c);
        System.out.println(Child_list);
        Main.systemObjects.add(c);
        return c;
    }
    public void createBracelet(Child c){
        Bracelet b=new Bracelet();
        c.add_Bracelet(b);
    }
    public void deleteBracelet(Child c){
        c.deleteBracelet();
    }

    public void  deleteChild(Child c){
        this.Child_list.remove(c);
        Main.systemObjects.remove(c.e);
        Main.systemObjects.remove(c.b);

    }

    public void AddEntry(Child c,Device d){
        c.addEntry(d);
    }
    public  void DeleteEntry(Child c,Entry e)
    {
        c.deleteEntry(e);
    }
}
