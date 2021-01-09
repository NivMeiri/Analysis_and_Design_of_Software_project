public class CoolState extends  AbstractState {

    public CoolState(AirConditioner airco){
        this.airco=airco;
    }

    public void Entry(){
        System.out.println("MODE-COOL");
    }
    public void ChangeTemp(){
        if (this.airco.Room_temp+5<=this.airco.Chosen_temp){
            this.airco.OnState.CurrentState=this.airco.OnState.heatState;
            this.airco.OnState.CurrentState.Entry();
            this.airco.currentOperation.ChangeTemp();
        }
    }
}
