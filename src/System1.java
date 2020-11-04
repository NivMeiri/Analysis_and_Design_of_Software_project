import java.util.*;
import java.sql.Date;
public class System1 {
    private HashMap<String, WebUser> Webusers;
    private WebUser CurrentWebUser=null;
    private List<Order> Oreders;
    private Order LastOreder=null;
    private List<Product> Products;
    //-------------------------------------------
    private HashMap<Object, Integer> AllObjInSys;
    private int id=0;
    //-------------------------------------------
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
        Scanner sciny=new Scanner(System.in);
        System.out.println("Who would you to order from?: ");
        String name=sciny.nextLine();
        WebUser MyWeb =this.Webusers.get(name);//using the hashmap
        MyWeb.getShoppingCart().printProducts();
        System.out.println("Would you like to order one of our products? (y/n)");
        String ans = sciny.nextLine();
        while (ans != "n"){
            System.out.println("What product do you want to add to your Cart?: ");
            String item = sciny.nextLine();
            int index=-1;
            String num;
            int degel=1;
            while (degel==1) {
                for (int i = 0; i < MyWeb.getShoppingCart().getLineItems().size(); i++) {
                    if (MyWeb.getShoppingCart().getLineItems().get(i).getProduct().getName().equals(item)) {
                        index = i;
                        degel=0;
                        break; } }
                if (index == -1)
                    System.out.println("Please enter valid name!");
            }
            while (true) {
                System.out.println("How many units do you want?");
                num = sciny.nextLine();
                if (MyWeb.getShoppingCart().getLineItems().get(index).getQuantity() >= Integer.parseInt(num))
                    break;
                System.out.println("Please enter valid number!");
            }
        }

    }
    public void Check_Func(){
        System.out.println("Working?");
    }
    public void Display_Order(){
    }
    public void Link_Product(){}
    public void AddProduct(){}

    public void Delete_Product(String Product_name){
        //if no product's name was given. ?
        if(Product_name.length()==0){
            System.out.println("No product name is written. Please try again");
            return;
        }
        boolean foundAny = false;
        for(Product p: Products){
            if(p.getName().equals(Product_name)){
                foundAny =true;
                p.delete();
            }
        }
        //if name given isn't an existed product's name in system, then try again.
        if(!foundAny) {System.out.println("Product does not exist. Please try again")};
    }

    public void Show_all_Objects(){
        System.out.println("| Object |  id  |  name  |");
        Set<Object> objArr = AllObjInSys.keySet();
        for(Object obj: objArr){
            if(obj instanceof Product){
                System.out.println("| "+obj.getClass().getName()+" | "+AllObjInSys.get(obj)+" | "+((Product) obj).getName()+" |");
            }
            else if(obj instanceof Order){
                System.out.println("| "+obj.getClass().getName()+" | "+AllObjInSys.get(obj)+" | "+((Order) obj).getNumber()+" |");
            }
            else if(obj instanceof WebUser){
                System.out.println("| "+obj.getClass().getName()+" | "+AllObjInSys.get(obj)+" | "+((WebUser) obj).getLogin_id()+" |");
            }
            else if(obj instanceof Customer){
                System.out.println("| "+obj.getClass().getName()+" | "+AllObjInSys.get(obj)+" | "+((Customer) obj).getId()+" |");
            }
            //-------------TODO how many shall i do?-------
        }
    }

    public void Show_object_id(int id){
        Set<Object> objArr = AllObjInSys.keySet();
        for (Object obj : objArr) {
            if (AllObjInSys.get(obj).equals(id)) {
                System.out.println("System ID: "+id);
                System.out.println(obj.toString());
                return;
            }
        }
    }

}
