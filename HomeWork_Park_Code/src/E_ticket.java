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
        if(entries.contains(entries))
            entries.remove(e);
    }
    public boolean getEntry(Device d){
        for (Entry e: entries){
            if (e.d==d){
                return e.used;
            }
        }
        return  false;
    }
}
