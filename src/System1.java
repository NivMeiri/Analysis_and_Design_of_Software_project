import jdk.nashorn.internal.objects.Global;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
public class System1 {
    private HashMap<String, WebUser> Webusers;
    private WebUser CurrentWebUser=null;
    private List<Order> Oreders;
    private Order LastOreder=null;
    private List<Product>  Pruducts;
    public System1(){
        this.CurrentWebUser=null;
        this.LastOreder=null;
    }
    /*
    1) take input
    2) create a customer with null Account
    3) cerate an Account with null ShoppingCart
    4) finish the WebUser
    5) create ShoppingCart With WebUser, set cart to Account

    */
    public void  Add_WebUser(String ID){
        System.out.println("Enter your password: ");
        Scanner s = new Scanner(System.in);
        String Pass = s.nextLine();
        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        System.out.println("Please enter your addres: ");
        Address newAddres = new Address("street",1,"city","state",32942);          /////TODO fix here///////
        String myAddress=s.nextLine();
        System.out.println(myAddress);
        System.out.println("Please enter your Phone Number: ");
        String pNumber = s.nextLine();
        System.out.println("Please enter your Email: ");
        String eMail = s.nextLine();
        Customer newCustomer = new Customer(ID,newAddres,pNumber,eMail,null);
        Account a1=null;
        switch (YesOrNo){

            case "y":
                 a1 = new PremiumAccount(ID,"new addressi",false,new  Date(2020,10,1),null,
                        0,newCustomer,null);
                 newCustomer.SetAccount(a1);
                 break;
            case "n":
                 a1 = new Account(ID,"newAddres",false,new Date(2019,10,8),null,
                        0,newCustomer,null);
                newCustomer.SetAccount(a1);
                break;
        }

        WebUser myUser = new WebUser(ID,Pass,UserState.New,newCustomer);
        ShoppingCart shop1 = new ShoppingCart(new Date(2020,9,9),myUser,a1);
        if (a1 != null) {
            a1.setShoppingCart(shop1);
            myUser.setShoppingCart(shop1);
        }
        System.out.println(a1 instanceof PremiumAccount);
        System.out.println(a1 instanceof Account);

        System.out.println(myUser.toString());
        System.out.println(newCustomer.toString());
    }
    public void Remove_Webuser(String Login_id){
        this.Webusers.get(Login_id).delete();
    }
    public void Login(String Login_id){
        if(this.Webusers.get(Login_id)==null) {
            System.out.println("The user is not exist!!");
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your Password");
        String Pass = s.nextLine();
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
