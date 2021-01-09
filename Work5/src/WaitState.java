public class WaitState extends  AbstractState {
    AirConditioner airco;
    public  WaitState(AirConditioner airco){
        this.airco=airco;
    }
    public void entry(){
        System.out.println("WAITING 30 SECONDS");
        airco.OnOffState=airco.OnState;
        airco.OnOffState.entry();
    }
}
