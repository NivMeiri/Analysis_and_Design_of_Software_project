import jdk.nashorn.internal.objects.Global;

import java.util.*;
import java.sql.Date;

public class System1 {
    private WebUser CurrentWebUser = null;
    private Order LastOreder = null;
    private int OrderNum = 0;
    private int id = 0;

    private HashMap<String, WebUser> Webusers;
    private LinkedList<Order> Oreders;
    private HashMap<String, Supplier> Suppliers;
    private LinkedList<Product> Products;
    private HashMap<Object, Integer> AllObjInSys_obj;
    private HashMap<Integer, Object> AllObjInSys_id;
    private LinkedList<Account>Accounts;
    private LinkedList<Customer>Customers;


    public System1(){
        this.CurrentWebUser=null;
        this.LastOreder=null;
        this.Webusers=new HashMap<String, WebUser>();
        this.Suppliers= new HashMap<String, Supplier>();
        this.Products=new LinkedList<Product>();
        this.Oreders=new LinkedList<Order>();
    }

    /*
    1) take input
    2) create a customer with null Account
    3) cerate an Account with null ShoppingCart
    4) finish the WebUser
    5) create ShoppingCart With WebUser, set cart to Account
*/
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
        System.out.println("Please enter your Phone Number: ");
        String pNumber = s.nextLine();
        System.out.println("Please enter your Email: ");
        String eMail = s.nextLine();
        Customer newCustomer = new Customer(ID,newAddress,pNumber,eMail,null);
        Account NewAccount=null;
        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        switch (YesOrNo){
            case "y":
                 NewAccount = new PremiumAccount(ID,"new addressi",false,new  Date(2020,10,1),null,
                        0,newCustomer,null);
                 newCustomer.SetAccount(NewAccount);
                 break;
            case "n":
                 NewAccount = new Account(ID,"newAddres",false,new Date(2019,10,8),null,
                        0,newCustomer,null);
                newCustomer.SetAccount(NewAccount);
                break;

            default:
                System.out.println("Are you a Premium User? (y/n)");
                 YesOrNo = s.nextLine();

        }

        WebUser myUser = new WebUser(ID,Pass,UserState.New,newCustomer);
        this.Webusers.put(ID,myUser);
        ShoppingCart shop1 = new ShoppingCart(new Date(2020,9,9),myUser,NewAccount);
        if (NewAccount != null) {
            NewAccount.setShoppingCart(shop1);
            myUser.setShoppingCart(shop1);
        }
        System.out.println(NewAccount instanceof PremiumAccount);
        System.out.println(NewAccount instanceof Account);
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
        else if(this.CurrentWebUser==null) {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter your Password");
            String Pass = s.nextLine();
            if (this.Webusers.get(Login_id).getPassword().equals(Pass)){
                System.out.println("You've logged in successfully");
                this.CurrentWebUser=this.Webusers.get(Login_id);
            }
        }
        else
            System.out.println("Someone is already logged in");
    }

    public void LogOut() {
           this.CurrentWebUser=null;
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
            if (MyWeb==null)
                System.out.println("Please enter valid name");
            else if (!(MyWeb.getCustomer().getAccount()  instanceof PremiumAccount))
                System.out.println("You can only order from a premium account");
            else
                break;

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

                myPayment = new ImmediatePayment(this.CurrentWebUser.getLogin_id(), new Date(2020,7,7), myOrder.getTotal(), "", MyWeb.getCustomer().getAccount(), myOrder,true);
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
                myPayment = new DelayedPayment(this.CurrentWebUser.getLogin_id(),new Date(2020,3,7), myOrder.getTotal(), "", MyWeb.getCustomer().getAccount(), myOrder,new Date(2020,9,9));
                myOrder.addPayment(myPayment);
                break;
            default:
        }
    }

    public void Check_Func() {
        System.out.println("Working?");
    }



    public void Link_Product(String product) {
           if (this.CurrentWebUser.getCustomer().getAccount() instanceof PremiumAccount){
               for (int i = 0; i <Products.size() ; i++) {
                   if (Products.get(i).getName().equals(product)){
                       Product p=Products.get(i);
                       ((PremiumAccount)this.CurrentWebUser.getCustomer().getAccount()).addProduct(p);
                       return;
                   }
               }
           }
           System.out.println("You are not a premium account");
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
        //if name given isn't an existing product's name in system, then try again.
        if (!foundAny) {
            System.out.println("Product does not exist. Please try again");
        }
    }
    public void Show_all_Objects() {
        System.out.println("| Object |  id  |  name  |");
        Set<Object> objArr = AllObjInSys_obj.keySet();
        for (Object obj : objArr) {
            if (obj instanceof Product) {
                System.out.println("| Product  | " + AllObjInSys_obj.get(obj) + " | " + ((Product) obj).getName() + " |");
            } else if (obj instanceof Order) {
                System.out.println("| Order | " + AllObjInSys_obj.get(obj) + " | " + ((Order) obj).getNumber() + " |");
            } else if (obj instanceof WebUser) {
                System.out.println("| WebUser | " + AllObjInSys_obj.get(obj) + " | " + ((WebUser) obj).getLogin_id() + " |");
            } else if (obj instanceof Customer) {
                System.out.println("| Customer | " + AllObjInSys_obj.get(obj) + " | " + ((Customer) obj).getId() + " |");
            } else if (obj instanceof ShoppingCart){
                System.out.println("| Shopping Cart | " + AllObjInSys_obj.get(obj) + " | " + ((ShoppingCart) obj).getCretaed() + " |");
            } else if (obj instanceof Account){
                System.out.println("| Account | " + AllObjInSys_obj.get(obj) + " | " + ((Account) obj).getID() + " |");
            } else if (obj instanceof PremiumAccount){
                System.out.println("| PremiumAccount | " + AllObjInSys_obj.get(obj) + " | " + ((PremiumAccount) obj).getID() + " |");
            } else if (obj instanceof LineItem){
                System.out.println("| LineItem | " + AllObjInSys_obj.get(obj) + " | " + ((LineItem) obj).getProduct().getName() + " |");
            } else if (obj instanceof Payment){
                System.out.println("| Payment | " + AllObjInSys_obj.get(obj) + " | " + ((Payment) obj).getId() + " |");
            } else if (obj instanceof DelayedPayment){
                System.out.println("| DelayedPayment | " + AllObjInSys_obj.get(obj) + " | " + ((DelayedPayment) obj).getId() + " |");
            } else if(obj instanceof  ImmediatePayment){
                System.out.println("| ImmediatePayment | " + AllObjInSys_obj.get(obj) + " | " + ((ImmediatePayment) obj).getId() + " |");
            } else if(obj instanceof Address){
                System.out.println("| Address | " + AllObjInSys_obj.get(obj) + " | " + ((Address) obj).getZipCode() + " |");
            }
        }
    }
    public void Show_object_id(int id) {
        Object obj = AllObjInSys_id.get(id);
        System.out.println(obj.getClass().getName()+" ID: " + id);
        obj.toString();
        /*if (obj instanceof Product) {
            ((Product)obj).toString();
        } else if (obj instanceof Order) {
            ((Order)obj).toString();
        } else if (obj instanceof WebUser) {
            ((WebUser)obj).toString();
        } else if (obj instanceof Customer) {
            ((Customer)obj).toString();
        } else if (obj instanceof ShoppingCart){
             ((ShoppingCart)obj).toString();
        } else if (obj instanceof Account){
             ((Account)obj).toString();
        } else if (obj instanceof PremiumAccount){
             ((PremiumAccount)obj).toString();
        } else if (obj instanceof LineItem){
             ((LineItem)obj).toString();
        } else if (obj instanceof Payment){
             ((Payment)obj).toString();
        } else if (obj instanceof DelayedPayment){
             ((DelayedPayment)obj).toString();
        } else if(obj instanceof  ImmediatePayment){
             ((ImmediatePayment)obj).toString();
        } else if(obj instanceof Address){
             ((Address)obj).toString();
        }*/
        return;
    }
}

