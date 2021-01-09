public class OnState extends  AbstractState{
    AbstractOp coolingop;
    AbstractOp heatingop;
    AbstractOp fanningop;

    AbstractState heatState;
    AbstractState coolState;

    AirConditioner airConditioner;

    AbstractOp CurrentOp;
    AbstractState CurrentState;
    AbstractState offState;

    public OnState(AirConditioner airco){
        this.airConditioner=airco;

        this.coolingop=new CoolingOp();
        this.heatingop=new HeatingOp();
        this.fanningop=new FanningOp();
        this.offState=new OffState();
        this.coolState=new CoolState();
        this.heatState=new HeatState();

    }
    public  void SetState(AbstractState state){
        this.CurrentState=state;
    }
    public void SetOp(AbstractOp oper){
        this.CurrentOp=oper;
    }
    public  void on(){
        System.out.println("The Air-conditioner already on");
    }
    public void off(){
        this.airConditioner.OnOffState=this.offState;
        System.out.println("OFF");
    }
    private void Entry(){
        System.out.println("ON");
        this.CurrentOp=this.fanningop;
        this.CurrentOp.Entry();
        this.CurrentState=this.coolState;
        this.CurrentState.entry();

    }
    public int getRoomtemp(){
        return this.airConditioner.Room_temp;
    }
    public int getChoosentemp(){
        return this.airConditioner.Chosen_temp;
    }
}
