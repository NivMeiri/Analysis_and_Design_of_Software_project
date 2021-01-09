public class HeatingOp extends  AbstractOp {

    public HeatingOp(AirConditioner airco){
        this.airco=airco;
    }

    public void Entry(){
        this.Entry("HEATING");
    }
    public void ChangeTemp(){
        if (this.airco.Room_temp>=this.airco.Chosen_temp+2){
            this.airco.currentOperation=this.airco.OnState.fanningop;
            this.airco.currentOperation.Entry();
            this.airco.currentOperation.ChangeTemp();
        }
    }
}
