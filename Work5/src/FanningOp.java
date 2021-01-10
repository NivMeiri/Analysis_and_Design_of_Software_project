public class FanningOp extends  AbstractOp{

    public FanningOp(AirConditioner airco){
        this.airco=airco;
    }
    public void Entry(){
        this.Entry("FANNING (120 SECONDS AT LEAST)");
    }
    public void ChangeTemp(){
        if (this.airco.OnState.CurrentState instanceof HeatState && this.airco.Room_temp< this.airco.Chosen_temp-2){
            this.airco.currentOperation=this.airco.OnState.heatingop;
            this.airco.currentOperation.Entry();
            this.airco.currentOperation.ChangeTemp();
        }
        else if(this.airco.OnState.CurrentState instanceof CoolState && this.airco.Room_temp>= this.airco.Chosen_temp+2){
                this.airco.currentOperation=this.airco.OnState.coolingop;
                this.airco.currentOperation.Entry();
                this.airco.currentOperation.ChangeTemp();
        }
    }
}
