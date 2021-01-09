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
        this.coolingop=new CoolingOp(airco);
        this.heatingop=new HeatingOp(airco);
        this.fanningop=new FanningOp(airco);
        this.offState=new OffState(airco);
        this.coolState=new CoolState(airco);
        this.heatState=new HeatState(airco);

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
        this.airConditioner.OnOffState.Entry();
    }
    public void Entry(){
        System.out.println("ON");
        this.CurrentState=this.coolState;
        this.CurrentState.Entry();
        this.CurrentOp=this.fanningop;
        this.CurrentOp.Entry();

    }
    public int getRoomtemp(){
        return this.airConditioner.Room_temp;
    }
    public int getChoosentemp(){
        return this.airConditioner.Chosen_temp;
    }
}
