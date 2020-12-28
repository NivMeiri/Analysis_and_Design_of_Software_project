import java.util.ArrayList;

public class Device {
int minAge;
double minHeight;
double minWeight;
boolean isOpen;
boolean isValid;
int price;
int time;
boolean isExtreme;
String name;
ArrayList<Child> currentUsing=new ArrayList<Child>();
public  Device(boolean isexe,double minHeight,int minWeight ,int minage,String name){
    this.isExtreme=isexe;
    this.minHeight=minHeight;
    this.minWeight=minWeight;
    this.minAge=minage;
    this.name=name;
    this.time=1000;
}
public void addChild(Child c){
    this.currentUsing.add(c);
}
public void removeChild(Child c){
    this.currentUsing.remove(c);
}
public boolean canGoOn(E_ticket e,Child c){
    if(c.age>=this.minAge && c.Height>=minHeight && c.Weight>=minWeight   )
        return true;
    else
        return false;
}
public boolean isOpen(){
    return isOpen;
}
public boolean isValid(){
    return isValid;
}
public boolean isExtreme(){
        return isExtreme;
    }
}
