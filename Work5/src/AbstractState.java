public class AbstractState {
    AirConditioner airco;
    public void Entry(){ }

    public void on(){ System.out.println("ERROR");}
    public void ChangeTemp(){}
    public void off(){System.out.println("ERROR");}
}
