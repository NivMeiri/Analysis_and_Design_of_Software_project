public class AirConditioner {
   int Room_temp=25;
   int Chosen_temp=25;
   AbstractState  OnOffState;
   AbstractState OnState;
   AbstractState OffState;
   AbstractState WaitState;

   public AirConditioner(){
      System.out.println("OFF");
      this.OnState=new OnState(this);
      this.OffState=new OffState(this);
      this.OnOffState=this.OffState;
      this.WaitState=new WaitState(this);

   }

   public void on(){

   }
   public void off() {

   }
   public void setC_temp(int temp) {
      this.Chosen_temp=temp;

   }
  public void setR_temp(int temp){
      this.Room_temp=temp;

  }

}
