public class CoolingOp extends AbstractOp{

    public CoolingOp(AirConditioner airco){
        this.airco=airco;

    }
    public void Entry(){
        this.Entry("COOLING");
    }
    public void ChangeTemp(){
        if (this.airco.Room_temp<=this.airco.Chosen_temp-2){
            this.airco.currentOperation=this.airco.OnState.fanningop;
            this.airco.currentOperation.Entry();
            this.airco.currentOperation.ChangeTemp();
        }
    }

}
