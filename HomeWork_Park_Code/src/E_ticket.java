import java.util.ArrayList;
import java.util.List;

public class E_ticket {
    Child c;
    int time;
    List<Entry> entries= new ArrayList<Entry>();

    public E_ticket(int time){
        this.time=time;
    }
    public void AddEntry(Entry e){
        if(e!=null)
            entries.add(e);
    }
    public void DeleteEntry(Entry e){
        if(entries.contains(e)) {
            entries.remove(e);
            Main.systemObjects.remove(e);
        }
    }
    public boolean getEntry(Device d){
        for (Entry e: entries){
            if (e.d==d){
                return e.used;
            }
        }
        return  false;
    }
    public void showTicket(){
        System.out.println("All entries in the ticket:");
        if (this.entries.size()==0)
            System.out.println("There is no entries in the ticket");
        else
            for (Entry e:this.entries){
                System.out.println("Device name: "+e.d.name);
            }
    }
}
