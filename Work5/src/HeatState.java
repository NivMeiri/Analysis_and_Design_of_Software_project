public class HeatState extends  AbstractState {

    public HeatState(AirConditioner airco){
        this.airco=airco;
    }
    public void Entry(){
        System.out.println("MODE-HEAT");
    }
    public void ChangeTemp(){
        if (this.airco.Room_temp-5>=this.airco.Chosen_temp){
            this.airco.OnState.CurrentState=this.airco.OnState.coolState;
            this.airco.OnState.CurrentState.Entry();
            this.airco.currentOperation.ChangeTemp();
        }
    }
}
