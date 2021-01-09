public class OffState extends AbstractState {
    AirConditioner airco;
    public OffState(AirConditioner airco){
        this.airco=airco;
    }

    public void Entry(){
        System.out.println("OFF");

    }

    public void on(){
        airco.OnOffState=airco.WaitState;
        airco.OnOffState.Entry();

        }
    public void ChangeTemp(){}
    public void off(){System.out.println("AirConditioner already in off state");}

}
