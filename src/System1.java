import jdk.nashorn.internal.objects.Global;

import java.util.*;
import java.sql.Date;
public class System1 {
    private HashMap<String, WebUser> Webusers;
    private WebUser CurrentWebUser = null;
    private List<Order> Oreders;
<<<<<<< HEAD
    private Order LastOreder = null;
    private int OrderNum = 0;
    private Dictionary<String, Supplier> Suppliers;
    private List<Product> Products;
    private HashMap<Object, Integer> AllObjInSys;
    private int id = 0;

    public System1() {
        this.CurrentWebUser = null;
        this.LastOreder = null;
=======
    private Order LastOreder=null;
    private List<Product>  Pruducts;
    public System1(){
        this.CurrentWebUser=null;
        this.LastOreder=null;
        this.Webusers=new HashMap<String, WebUser>();
>>>>>>> Niv's-Branch--System1
    }

    /*
    1) take input
    2) create a customer with null Account
    3) cerate an Account with null ShoppingCart
    4) finish the WebUser
    5) create ShoppingCart With WebUser, set cart to Account

    */
<<<<<<< HEAD
    public void Add_WebUser(String ID) {
        System.out.println("Enter your password: ");
        Scanner s = new Scanner(System.in);
        String Pass = s.nextLine();
        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        System.out.println("Please enter your addres: ");
        Address newAddres = new Address("street", 1, "city", "state", 32942);          /////TODO fix here///////
        String myAddress = s.nextLine();
        System.out.println(myAddress);
=======
    public void  Add_WebUser(String ID){
        if (this.Webusers.containsKey(ID)){
            System.out.println("This user already exists");
            return;
        }
        System.out.println("Enter your password: ");
        Scanner s = new Scanner(System.in);
        String Pass = s.nextLine();

        System.out.println("Please enter your address: ");
        String Address = s.nextLine();
        Address newAddress = new Address(Address);          /////TODO fix here///////
>>>>>>> Niv's-Branch--System1
        System.out.println("Please enter your Phone Number: ");
        String pNumber = s.nextLine();
        System.out.println("Please enter your Email: ");
        String eMail = s.nextLine();
<<<<<<< HEAD
        Customer newCustomer = new Customer(ID, newAddres, pNumber, eMail, null);
        Account a1 = null;
        switch (YesOrNo) {
=======
        Customer newCustomer = new Customer(ID,newAddress,pNumber,eMail,null);
        Account NewAccount=null;
>>>>>>> Niv's-Branch--System1

        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        switch (YesOrNo){
            case "y":
<<<<<<< HEAD
                a1 = new PremiumAccount(ID, "new addressi", false, new Date(2020, 10, 1), null,
                        0, newCustomer, null);
                newCustomer.SetAccount(a1);
                break;
            case "n":
                a1 = new Account(ID, "newAddres", false, new Date(2019, 10, 8), null,
                        0, newCustomer, null);
                newCustomer.SetAccount(a1);
=======
                 NewAccount = new PremiumAccount(ID,"new addressi",false,new  Date(2020,10,1),null,
                        0,newCustomer,null);
                 newCustomer.SetAccount(NewAccount);
                 break;
            case "n":
                 NewAccount = new Account(ID,"newAddres",false,new Date(2019,10,8),null,
                        0,newCustomer,null);
                newCustomer.SetAccount(NewAccount);
>>>>>>> Niv's-Branch--System1
                break;

            default:
                System.out.println("Are you a Premium User? (y/n)");
                String YesOrNo = s.nextLine();


        }

<<<<<<< HEAD
        WebUser myUser = new WebUser(ID, Pass, UserState.New, newCustomer);
        ShoppingCart shop1 = new ShoppingCart(new Date(2020, 9, 9), myUser, a1);
        if (a1 != null) {
            a1.setShoppingCart(shop1);
=======
        WebUser myUser = new WebUser(ID,Pass,UserState.New,newCustomer);
        ShoppingCart shop1 = new ShoppingCart(new Date(2020,9,9),myUser,NewAccount);
        if (NewAccount != null) {
            NewAccount.setShoppingCart(shop1);
>>>>>>> Niv's-Branch--System1
            myUser.setShoppingCart(shop1);
        }
        System.out.println(NewAccount instanceof PremiumAccount);
        System.out.println(NewAccount instanceof Account);
        System.out.println(myUser.toString());
        System.out.println(newCustomer.toString());
    }

    public void Remove_Webuser(String Login_id) {
        this.Webusers.get(Login_id).delete();
    }

    public void Login(String Login_id) {
        if (this.Webusers.get(Login_id) == null) {
            System.out.println("The user is not exist!!");
        }
        if(this.CurrentWebUser!=null)
            //Scanner s = new Scanner(System.in);
        System.out.println("Please enter your Password");
       // String Pass = s.nextLine();
    }

    public void LogOut() {
    }

    public void order() {
    }

    public void MakeOrder() {
        Order myOrder = new Order(String.valueOf(OrderNum), new Date(2000, 10, 18), null, this.CurrentWebUser.getCustomer().getAddress(), OrderStatus.New, 0, this.CurrentWebUser.getCustomer().getAccount());
        OrderNum++;
        Scanner sciny = new Scanner(System.in);
        /// TODO CHECK IF THE WEBUSER EXIST ///
        WebUser MyWeb;
        while (true) {
            System.out.println("Who would you like to order from?: ");
            String name = sciny.nextLine();
            MyWeb = this.Webusers.get(name);//using the hashmap
            if (MyWeb != null)
                break;
            System.out.println("Please enter valid name");
        }
        MyWeb.getShoppingCart().printProducts();
        /// TODO CHECK IF THERE IS A PRODUCTS ///
        if (MyWeb.getShoppingCart().numberOfLineItems() == 0) {
            System.out.println("The WebUser has no products");
            return;
        }

        System.out.println("Would you like to order one of our products? (y/n)");
        String ans = sciny.nextLine();
        while (ans == "y") {
            System.out.println("What product do you want to add to your Cart?: ");
            String item = sciny.nextLine();
            LineItem UserItem = null;
            int num;
            while (UserItem == null) {
                for (int i = 0; i < MyWeb.getShoppingCart().getLineItems().size(); i++) {
                    if (MyWeb.getShoppingCart().getLineItems().get(i).getProduct().getName().equals(item)) {
                        UserItem = MyWeb.getShoppingCart().getLineItems().get(i);
                        break;
                    }
                }
                if (UserItem == null)
                    System.out.println("Please enter a valid name!");
            }
            while (true) {
                System.out.println("How many units do you want?");
                num = Integer.parseInt(sciny.nextLine());
                if (UserItem.getQuantity() >= num) {
                    if (myOrder.getTotal() + (UserItem.getPrice() * num) < this.CurrentWebUser.getCustomer().getAccount().getBalance())
                        break;
                    System.out.println("You don't have enough money, try ordering less");
                } else {
                    System.out.println("Please enter valid number!");
                }
            }
            LineItem myItem = new LineItem(num, UserItem.getPrice(), this.CurrentWebUser.getShoppingCart(), myOrder, UserItem.getProduct());
            UserItem.setQuantity(UserItem.getQuantity() - num);
            myOrder.addLineItem(myItem);
            myOrder.setTotal(myOrder.getTotal() + myItem.getPrice() * num);
            this.CurrentWebUser.getShoppingCart().addLineItem(myItem);
            System.out.println("Would you like to order something else? y/n");
            ans = sciny.nextLine();
        }
        System.out.println("Do you want to pay now? y/n");
        ans = sciny.nextLine();
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
                String day = sciny.nextLine();
                System.out.println("insert month between 1-12:");
                String month = sciny.nextLine();
                System.out.println("insert year between 2020-2021:");
                String year = sciny.nextLine();
                myPayment = new DelayedPayment(this.CurrentWebUser.getLogin_id(),new Date(2020,3,7), myOrder.getTotal(), "", MyWeb.getCustomer().getAccount(), myOrder);
                myOrder.addPayment(myPayment);
                break;
            default:
        }
    }

    public void Check_Func() {
        System.out.println("Working?");
    }



    public void Link_Product() {
    }

    public void Display_Order()
    ///****Ori wrote****
    {
        if (this.CurrentWebUser==null)
        {
            System.out.println("There is no active user in the system");
        }
        else
        {
            if (this.CurrentWebUser.getCustomer().getAccount().getOrders().size() < 1)
            {
                System.out.println("The customer has no orders");
            }
            else
            {
                Order o = this.CurrentWebUser.getCustomer().getAccount().getOrder(-1);/// check with Hadassa - if the new order will
                /// be in the first or the last place in the list
                System.out.println("Number " + o.getNumber() + "\n");
                System.out.println("Ordered in " + o.getOrederd() + "\n");
                System.out.println("Shipped in " + o.getShipped() + "\n");
                System.out.println("Address " + o.getShip_to()+ "\n");
                System.out.println("Status " + o.getStatus()+ "\n");
                System.out.println("Total " + o.getTotal() + "\n");
            }
        }
    }
    public void Niv_Branch(){};
    public void AddProduct()
    {
        Supplier tmpSup;
        System.out.println("Please enter Supplier id\n");
        Scanner s = new Scanner(System.in);
        String id = s.nextLine();
        if (Suppliers.get(id) ==null)
        {       System.out.println("Supplier's Id is unidentified");
                System.out.println("Please enter Supplier name\n");
                String name = s.nextLine();
                tmpSup=new Supplier(id,name);
                Suppliers.put(id,tmpSup);
        }
        else
        {
            tmpSup = Suppliers.get(id);
        }
        System.out.println("Please enter Product name\n");
        String productName = s.nextLine();
        System.out.println("Please enter Product id\n");
        String productid = s.nextLine();
        Product newProduct = new Product(productid ,productName, tmpSup) ;
        System.out.println("New product added\n");
        Products.add(newProduct);
        tmpSup.addProduct(productid,productName);

    }
    public void Delete_Product(String Product_name) {
        //if no product's name was given. ?
        if (Product_name.length() == 0) {
            System.out.println("No product name is written. Please try again");
            return;
        }
        boolean foundAny = false;
        for (Product p : Products) {
            if (p.getName().equals(Product_name)) {
                foundAny = true;
                p.delete();
            }
        }
        //if name given isn't an existed product's name in system, then try again.
        if (!foundAny) {
            System.out.println("Product does not exist. Please try again");
        } ;
    }
    public void Show_all_Objects() {
        System.out.println("| Object |  id  |  name  |");
        Set<Object> objArr = AllObjInSys.keySet();
        for (Object obj : objArr) {
            if (obj instanceof Product) {
                System.out.println("| " + obj.getClass().getName() + " | " + AllObjInSys.get(obj) + " | " + ((Product) obj).getName() + " |");
            } else if (obj instanceof Order) {
                System.out.println("| " + obj.getClass().getName() + " | " + AllObjInSys.get(obj) + " | " + ((Order) obj).getNumber() + " |");
            } else if (obj instanceof WebUser) {
                System.out.println("| " + obj.getClass().getName() + " | " + AllObjInSys.get(obj) + " | " + ((WebUser) obj).getLogin_id() + " |");
            } else if (obj instanceof Customer) {
                System.out.println("| " + obj.getClass().getName() + " | " + AllObjInSys.get(obj) + " | " + ((Customer) obj).getId() + " |");
            }
            //-------------TODO how many shall i do?-------
        }
    }
    public void Show_object_id(int id) {
        Set<Object> objArr = AllObjInSys.keySet();
        for (Object obj : objArr) {
            if (AllObjInSys.get(obj).equals(id)) {
                System.out.println("System ID: " + id);
                System.out.println(obj.toString());
                return;
            }
        }
    }
}

