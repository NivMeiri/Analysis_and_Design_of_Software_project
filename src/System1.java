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
    private int OrderNum=0;
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
        Order myOrder=new Order(String.valueOf(OrderNum),new Date(2000,10,18),null,this.CurrentWebUser.getCustomer().getAddress(),OrderStatus.New,0,this.CurrentWebUser.getCustomer().getAccount());
        OrderNum++;
        Scanner sciny=new Scanner(System.in);
        /// TODO CHECK IF THE WEBUSER EXIST ///
        WebUser MyWeb;
        while (true) {
            System.out.println("Who would you like to order from?: ");
            String name = sciny.nextLine();
            MyWeb = this.Webusers.get(name);//using the hashmap
            if (MyWeb !=null)
                break;
            System.out.println("Please enter valid name");
        }
        MyWeb.getShoppingCart().printProducts();
        /// TODO CHECK IF THERE IS A PRODUCTS ///
        if(MyWeb.getShoppingCart().numberOfLineItems()==0){
            System.out.println("The WebUser has no products");
            return;
        }

        System.out.println("Would you like to order one of our products? (y/n)");
        String ans = sciny.nextLine();
        while (ans == "y"){
            System.out.println("What product do you want to add to your Cart?: ");
            String item = sciny.nextLine();
            LineItem UserItem=null;
            int num;
            while (UserItem==null) {
                for (int i = 0; i < MyWeb.getShoppingCart().getLineItems().size(); i++) {
                    if (MyWeb.getShoppingCart().getLineItems().get(i).getProduct().getName().equals(item)) {
                        UserItem=MyWeb.getShoppingCart().getLineItems().get(i);
                        break; } }
                if (UserItem==null)
                    System.out.println("Please enter a valid name!");
            }
            while (true) {
                System.out.println("How many units do you want?");
                num = Integer.parseInt(sciny.nextLine());
                if (UserItem.getQuantity() >= num) {
                    if (myOrder.getTotal()+(UserItem.getPrice()*num)<this.CurrentWebUser.getCustomer().getAccount().getBalance())
                        break;
                    System.out.println("You don't have enough money, try ordering less");
                }
                else {
                    System.out.println("Please enter valid number!");
                }
            }
            LineItem myItem=new LineItem(num,UserItem.getPrice(),this.CurrentWebUser.getShoppingCart(),myOrder,UserItem.getProduct());
            UserItem.setQuantity(UserItem.getQuantity()-num);
            myOrder.addLineItem(myItem);
            myOrder.setTotal(myOrder.getTotal()+myItem.getPrice()*num);
            this.CurrentWebUser.getShoppingCart().addLineItem(myItem);
            System.out.println("Would you like to order something else? y/n");
            ans=sciny.nextLine();
        }
        System.out.println("Do you want to pay now? y/n");
        ans=sciny.nextLine();
        Payment myPayment;
        switch (ans) {
            case "y":
                myPayment = new ImmediatePayment(this.CurrentWebUser.getLogin_id(), Date, myOrder.getTotal(), "", MyWeb, myOrder);
                myPayment.make_payment();
                myOrder.addPayment(myPayment);
                break;
            case "n":
                System.out.println("When do you want to pay?");
                System.out.println("insert day between 1-31:");
                String day=sciny.nextLine();
                System.out.println("insert month between 1-12:");
                String month=sciny.nextLine();
                System.out.println("insert year between 2020-2021:");
                String year=sciny.nextLine();
                myPayment=new DelayedPayment(this.CurrentWebUser.getLogin_id(), date, myOrder.getTotal(), "", MyWeb, myOrder);
                myOrder.addPayment(myPayment);
                break;
            default:
        }
    }

    public void Check_Func(){
        System.out.println("Working?"); }
    public void Display_Order(){ }
    public void Link_Product(){}
    public void AddProduct(){}
    public void Delete_Product(){}
    public void Show_all_Objects(){
    }
    public void Show_object_id(){}

}
