import jdk.nashorn.internal.objects.Global;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
public class System1 {
    private List<WebUser> Webusers;
    private WebUser CurrentWebUser=null;
    private List<Order> Oreders;
    private Order LastOreder=null;
    private List<Product>  Pruducts;
    public System1(){
        this.CurrentWebUser=null;
        this.LastOreder=null;
    }
    public void  Add_WebUser(String ID){
        System.out.println("Enter your password: ");
        Scanner s = new Scanner(System.in);
        String Pass = s.nextLine();
        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        System.out.println("Please enter your addres: ");
        String addres = s.nextLine();
        System.out.println("Please enter your Phone Number: ");
        String pNumber = s.nextLine();
        System.out.println("Please enter your Email: ");
        String eMail = s.nextLine();

        switch (YesOrNo){
            case "y":
                Account a1 = new PremiumAccount(ID,addres,false,new java.sql.Date(),null,
                        0,);
        }

        //Customer newCustomer = new Customer(ID,)


        //WebUser newUser = new WebUser(ID,Pass,UserState.New,)
    }
    public void Remove_Webuser(){
    }
    public void Login(){
    }
    public void LogOut(){
    }
    public void order(){
    }
    public void MakeOrder(){
    }
    public void Display_Order(){
    }
    public void Link_Product(){}
    public void AddProduct(){}
    public void Delete_Product(){}
    public void Show_all_Objects(){
    }
    public void Show_object_id(){}

}
