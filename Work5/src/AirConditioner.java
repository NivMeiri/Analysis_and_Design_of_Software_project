public class AirConditioner {
   int Room_temp=25;
   int Chosen_temp=25;
   AbstractState  OnOffState;
   OnState OnState;
   OffState OffState;
   AbstractState WaitState;
   AbstractOp currentOperation;

   public AirConditioner(){
      System.out.println("OFF");
      this.OnState=new OnState(this);
      this.OffState=new OffState(this);
      this.OnOffState=this.OffState;
      this.WaitState=new WaitState(this);
      this.currentOperation=new FanningOp(this);

   }

   public void on(){
      this.OnOffState.on();
   }

   public void off() {
      this.OnOffState.off();

   }
   public void setC_temp(int temp) {
      this.Chosen_temp=temp;
      System.out.println("set c_temp to "+temp);
      this.OnState.CurrentState.ChangeTemp();
      this.currentOperation.ChangeTemp();

   }
  public void setR_temp(int temp){
      this.Room_temp=temp;
     System.out.println("set r_temp to "+temp);
     this.OnState.CurrentState.ChangeTemp();
     this.currentOperation.ChangeTemp();


  }

}
