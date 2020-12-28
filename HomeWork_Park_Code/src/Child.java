import java.util.ArrayList;

public class Child {
Bracelet b;
E_ticket e;
boolean isOnDevice=false;
String name;
double Height,Weight;
int  ID;
int age;
public Child(int time){
    e=new E_ticket(time);
    b=new Bracelet();
}
public  void addEntry(Device d) {
    Entry entry=new Entry(d);
    e.AddEntry(entry);
    }

public void  deleteEntry(Entry e){
    this.e.DeleteEntry(e);
}
public  void deleteTicket(){
    this.e=null;
    System.out.println("Ticket deleted");
}
public void deleteBracelet(){
    this.b=null;
    System.out.println("Braclete deleted");
}
public void addE_ticket(E_ticket E){
    this.e=E;
}
public  void add_Bracelet(Bracelet B){
    this.b=B;
}
}
