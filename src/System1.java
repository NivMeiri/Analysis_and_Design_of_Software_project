import jdk.nashorn.internal.objects.Global;

import java.util.*;
import java.util.Date;

public class System1 {
    private WebUser CurrentWebUser = null;
    private Order LastOreder = null;
    private int OrderNum = 0;
    static int Static_Id = 1;
    private HashMap<String, WebUser> Webusers;
    private Vector<Order> Oreders;
    private HashMap<String, Supplier> Suppliers;
    private Vector<Product> Products;
    //todo- Check if deleting from dicts - while deleting webUser OR product - is working. if function 10 isnt printing them.
    static HashMap<Object, Integer> AllObjInSys_obj;
    static HashMap<Integer, Object> AllObjInSys_id;
    private Vector<Account> Accounts;
    private Vector<Customer> Customers ;

    public System1() {
        this.CurrentWebUser = null;
        this.LastOreder = null;
        this.Webusers = new HashMap<String, WebUser>();
        this.Suppliers = new HashMap<String, Supplier>();
        this.AllObjInSys_id=new HashMap<Integer, Object>();
        this.AllObjInSys_obj=new HashMap<Object, Integer>();
        this.Products = new Vector<>();
        this.Oreders = new Vector<>();
    }

    /*
    1) take input
    2) create a customer with null Account
    3) cerate an Account with null ShoppingCart
    4) finish the WebUser
    5) create ShoppingCart With WebUser, set cart to Account
*/
    public void Add_WebUser(String ID) {
        if (this.Webusers.containsKey(ID)) {
            System.out.println("This user already exists");
            return;
        }
        System.out.println("Enter your password: ");
        Scanner s = new Scanner(System.in);
        String Pass = s.nextLine();
        System.out.println("Please enter your address: ");
        String Address = s.nextLine();
        Address newAddress = new Address(Address);          /////TODO fix here///////
        //----------Insert to Dicts & increasing static----
        AllObjInSys_obj.put(newAddress,Static_Id);
        AllObjInSys_id.put(Static_Id,newAddress);
        Static_Id++;
        //-------------------------------------------------
        System.out.println("Please enter your Phone Number: ");
        String pNumber = s.nextLine();
        System.out.println("Please enter your Email: ");
        String eMail = s.nextLine();
        Customer newCustomer = new Customer(ID, newAddress, pNumber, eMail, null);
        //----------Insert to Dicts & increasing static----
        this.AllObjInSys_id.put(Static_Id,newCustomer);
        this.AllObjInSys_obj.put(newCustomer,Static_Id);
        Static_Id+=1;
        //-------------------------------------------------
        Account NewAccount = null;
        System.out.println("Are you a Premium User? (y/n)");
        String YesOrNo = s.nextLine();
        boolean accepted = false;
        while(!accepted) {
            switch (YesOrNo) {
                case "y":
                    NewAccount = new PremiumAccount(ID, "new addressi", false, new Date(), null,
                            0, newCustomer, null);
                    newCustomer.SetAccount(NewAccount);
                    accepted = true;
                    break;
                case "n":
                    NewAccount = new Account(ID, "newAddres", false, new Date(), null,
                            100, newCustomer, null);
                    newCustomer.SetAccount(NewAccount);
                    accepted = true;
                    break;

                default:
                    System.out.println("Are you a Premium User? (y/n)");
                    YesOrNo = s.nextLine();

            }
        }
        //----------Insert to Dicts & increasing static----
        AllObjInSys_obj.put(NewAccount,Static_Id);
        AllObjInSys_id.put(Static_Id,NewAccount);
        Static_Id++;
        //-------------------------------------------------
        WebUser myUser = new WebUser(ID, Pass, UserState.New, newCustomer);
        this.Webusers.put(ID, myUser);
        //----------Insert to Dicts & increasing static----
        AllObjInSys_obj.put(myUser,Static_Id);
        AllObjInSys_id.put(Static_Id,myUser);
        Static_Id++;
        //-------------------------------------------------
        ShoppingCart shop1 = new ShoppingCart(new Date(), myUser, NewAccount);
        //----------Insert to Dicts & increasing static----
        AllObjInSys_obj.put(shop1,Static_Id);
        AllObjInSys_id.put(Static_Id,shop1);
        Static_Id++;
        //-------------------------------------------------
        if (NewAccount != null) {
            NewAccount.setShoppingCart(shop1);
            myUser.setShoppingCart(shop1);
        }
        System.out.println(myUser.toString());
        System.out.println(newCustomer.toString());
    }

    public void Remove_Webuser(String Login_id) {

        WebUser userToRemove = this.Webusers.get(Login_id);
        this.Webusers.get(Login_id).delete();
    }

    public void Login(String Login_id) {
        if (this.Webusers.get(Login_id) == null) {
            System.out.println("Username is incorrect / doesn't exist - try again");
        }
        else if (this.CurrentWebUser == null) {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter your Password");
            String Pass = s.nextLine();
            if (this.Webusers.get(Login_id).getPassword().equals(Pass)) {

                this.CurrentWebUser = this.Webusers.get(Login_id);
                System.out.println("Login successful - Welcome Back!!");
            }
            else{
                System.out.println("Password is incorrect - try again");
            }
        }
        else
            System.out.println("Someone is already logged in");
    }

    public void LogOut(String userToLogout) {
        if(this.CurrentWebUser == null) {
            System.out.println("No one is connected right now");
        }
        else if( !this.CurrentWebUser.getLogin_id().equals(userToLogout)){
            System.out.println(userToLogout+" is not connected right now");
        }
        else{
                this.CurrentWebUser = null;
                System.out.println("Logout complete");
            }

    }
    public void order() {
    }

    public void MakeOrder() {
        if (this.CurrentWebUser == null) {
            System.out.println("Cannot order without user logged in");
            return;
        }
        if(this.CurrentWebUser.getCustomer().getAccount().getBalance() ==0 ) {
            System.out.println("Cannot make orders while balance is empty");
            return;
        }
        Order myOrder = new Order(String.valueOf(OrderNum), new Date(), null, this.CurrentWebUser.getCustomer().getAddress(), OrderStatus.New, 0, this.CurrentWebUser.getCustomer().getAccount());
        OrderNum++;
        //----------insert to Dicts & increasing static----
        AllObjInSys_obj.put(myOrder,Static_Id);
        AllObjInSys_id.put(Static_Id,myOrder);
        Static_Id++;
        //-------------------------------------------------
        Scanner sciny = new Scanner(System.in);
        /// TODO CHECK IF THE WEBUSER EXIST ///
        WebUser MyWeb;
        while (true) {
            System.out.println("Who would you like to order from?: ");
            String name = sciny.nextLine();
            MyWeb = this.Webusers.get(name);//using the hashmap
            if (MyWeb == null)
                System.out.println("The WebUser  is not exist, please try again");
            else if (!(MyWeb.getCustomer().getAccount() instanceof PremiumAccount))
                System.out.println("You can only order from a premium account");
            else
                break;
        }
        PremiumAccount premiumUser = (PremiumAccount) MyWeb.getCustomer().getAccount();
        premiumUser.printProducts();
        /// TODO CHECK IF THERE IS A PRODUCTS ///
        if (premiumUser.numberOfProducts() == 0) {
            System.out.println("The WebUser has no products");
            return;
        }
        System.out.println("Would you like to order one of the products? (y/n)");
        String ans = sciny.nextLine();
        while (ans.equals("y")) {
            System.out.println("What product do you want to add to your Cart?: ");
            String item = sciny.nextLine();
            Product UserItem = null;
            int num;
            while (UserItem == null) {
                for (int i = 0; i < premiumUser.numberOfProducts(); i++) {
                    if (premiumUser.getProducts().get(i).getName().equals(item)) {
                        UserItem = premiumUser.getProducts().get(i);
                        break;
                    }
                }
                if (UserItem == null) {
                    System.out.println("There is no product with this name!");
                    return;
                }
            }
            while (true) {
                System.out.println("How many units do you want?");
                num = Integer.parseInt(sciny.nextLine());
                if (UserItem.getAmount() >= num) {
                    if (myOrder.getTotal() + (UserItem.getPrice() * num) <= this.CurrentWebUser.getCustomer().getAccount().getBalance()) {
                        System.out.println("You don't have enough money, try ordering less");
                        break;
                    }
                    else{
                        LineItem myItem = new LineItem(num, UserItem.getPrice(), this.CurrentWebUser.getShoppingCart(), myOrder, UserItem);
                        //----------insert to Dicts & increasing static----
                        AllObjInSys_obj.put(myItem,Static_Id);
                        AllObjInSys_id.put(Static_Id,myItem);
                        Static_Id++;
                        //-------------------------------------------------
                        UserItem.setAmount(UserItem.getAmount() - num);
                        myOrder.addLineItem(myItem);
                        myOrder.setTotal(myOrder.getTotal() + myItem.getPrice() * num);
                        this.CurrentWebUser.getShoppingCart().addLineItem(myItem);
                        System.out.println("Would you like to order something else? y/n");
                        ans = sciny.nextLine();
                        if ( ans.equals("n")){
                            return;
                        }
                    }

                }
                else {
                    System.out.println("The amount of this product is :  "+UserItem.getAmount()+" and you asked for amount of :  "+num);
                    break;
                }
                System.out.println("Do  you want to exit or continue to try ordering ?  y/n");
                String enter = sciny.nextLine();
                if (enter.equals("n")) {
                    return;
                }
                if (enter.equals("y")) {
                    break;
                }
            }

        }
        System.out.println("Do you want to pay now? y/n");
        ans = sciny.nextLine();
        Payment myPayment;
        switch (ans) {
            case "y":

                myPayment = new ImmediatePayment(this.CurrentWebUser.getLogin_id(), new Date(2020, 7, 7), myOrder.getTotal(), "", MyWeb.getCustomer().getAccount(), myOrder, true);
                //----------insert to Dicts & increasing static----
                AllObjInSys_obj.put(myPayment,Static_Id);
                AllObjInSys_id.put(Static_Id,myPayment);
                Static_Id++;
                //-------------------------------------------------
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
                myPayment = new DelayedPayment(this.CurrentWebUser.getLogin_id(), new Date(2020, 3, 7), myOrder.getTotal(), "", MyWeb.getCustomer().getAccount(), myOrder, new Date(2020, 9, 9));
                //----------insert to Dicts & increasing static----
                AllObjInSys_obj.put(myPayment,Static_Id);
                AllObjInSys_id.put(Static_Id,myPayment);
                Static_Id++;
                //-------------------------------------------------
                myOrder.addPayment(myPayment);
                break;
            default:
        }
    }

    public void Check_Func() {
        System.out.println("Working?");
    }


    public void Link_Product(String product) {
        if (this.CurrentWebUser == null) {
            System.out.println("Cannot link a product while no user connected");
            return;
        }
        if (this.CurrentWebUser.getCustomer().getAccount() instanceof PremiumAccount) {
            for (int i = 0; i < Products.size(); i++) {
                if (Products.get(i).getName().equals(product)) {
                    Scanner sciny = new Scanner(System.in);
                    System.out.println("At what price would you like to sell the product?");
                    int price = Integer.valueOf(sciny.nextLine());
                    System.out.println("How many units do you have from this product?");
                    int amount = Integer.valueOf(sciny.nextLine());
                    Product p = Products.get(i);
                    p.setPrice(price);
                    p.setAmount(amount);
                    ((PremiumAccount) this.CurrentWebUser.getCustomer().getAccount()).addProduct(p);
                    return;
                }
            }
        }
        System.out.println("You are not a premium account");
    }

    public void Display_Order()
    ///****Ori wrote****
    {
        if (this.CurrentWebUser == null) {
            System.out.println("There is no active user in the system");
        } else {
            if (this.CurrentWebUser.getCustomer().getAccount().getOrders().size() < 1) {
                System.out.println("The customer has no orders");
            } else {
                Order o = this.CurrentWebUser.getCustomer().getAccount().getOrder(-1);/// check with Hadassa - if the new order will
                /// be in the first or the last place in the list
                System.out.println("Number " + o.getNumber() + "\n");
                System.out.println("Ordered in " + o.getOrederd() + "\n");
                System.out.println("Shipped in " + o.getShipped() + "\n");
                System.out.println("Address " + o.getShip_to() + "\n");
                System.out.println("Status " + o.getStatus() + "\n");
                System.out.println("Total " + o.getTotal() + "\n");
            }
        }
    }
    public void AddProduct() {
        Supplier tmpSup;
        System.out.println("Please enter Supplier id\n");
        Scanner s = new Scanner(System.in);
        String id = s.nextLine();
        if (Suppliers.get(id) == null) {
            System.out.println("Supplier's Id is unidentified");
            System.out.println("Please enter Supplier name\n");
            String name = s.nextLine();
            tmpSup = new Supplier(id, name);
            //----------insert to Dicts & increasing static----
            AllObjInSys_obj.put(tmpSup,Static_Id);
            AllObjInSys_id.put(Static_Id,tmpSup);
            Static_Id++;
            //-------------------------------------------------
            Suppliers.put(id, tmpSup);
        } else {
            tmpSup = Suppliers.get(id);
        }
        System.out.println("Please enter Product name\n");
        String productName = s.nextLine();
        System.out.println("Please enter Product id\n");
        String productid = s.nextLine();
        System.out.println("Please enter Product price\n");
        String price = s.nextLine();
        ///todo add input check
        Product newProduct = new Product(productid, productName, tmpSup, Integer.parseInt(price));
        //----------insert to Dicts & increasing static----
        AllObjInSys_obj.put(newProduct,Static_Id);
        AllObjInSys_id.put(Static_Id,newProduct);
        Static_Id++;
        //-------------------------------------------------
        System.out.println("New product added\n");
        Products.add(newProduct);
        tmpSup.addProduct(productid, productName);

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
        System.out.println("| Object   | id | name  |");
        Set<Object> objArr = AllObjInSys_obj.keySet();
        for (Object obj : this.AllObjInSys_obj.keySet()) {
            if (obj instanceof Product) {
                System.out.println("| Product  | " + AllObjInSys_obj.get(obj) + "  | " + ((Product) obj).getName() + " |");
            } else if (obj instanceof Order) {
                System.out.println("| Order | " + AllObjInSys_obj.get(obj) + "  | " + ((Order) obj).getNumber() + " |");
            } else if (obj instanceof WebUser) {
                System.out.println("| WebUser | " + AllObjInSys_obj.get(obj) + "  | " + ((WebUser) obj).getLogin_id() + " |");
            } else if (obj instanceof Customer) {
                System.out.println("| Customer | " + AllObjInSys_obj.get(obj) + "  | " + ((Customer) obj).getId() + " |");
            } else if (obj instanceof ShoppingCart) {
                System.out.println("| Shopping Cart | " + AllObjInSys_obj.get(obj) + "  | " + ((ShoppingCart) obj).getCretaed() + " |");
            } else if (obj instanceof Account) {
                System.out.println("| Account | " + AllObjInSys_obj.get(obj) + "  | " + ((Account) obj).getID() + " |");
            } else if (obj instanceof PremiumAccount) {
                System.out.println("| PremiumAccount | " + AllObjInSys_obj.get(obj) + "  | " + ((PremiumAccount) obj).getID() + " |");
            } else if (obj instanceof LineItem) {
                System.out.println("| LineItem | " + AllObjInSys_obj.get(obj) + "  | " + ((LineItem) obj).getProduct().getName() + " |");
            } else if (obj instanceof Payment) {
                System.out.println("| Payment | " + AllObjInSys_obj.get(obj) + "  | " + ((Payment) obj).getId() + " |");
            } else if (obj instanceof DelayedPayment) {
                System.out.println("| DelayedPayment | " + AllObjInSys_obj.get(obj) + "  | " + ((DelayedPayment) obj).getId() + " |");
            } else if (obj instanceof ImmediatePayment) {
                System.out.println("| ImmediatePayment | " + AllObjInSys_obj.get(obj) + "  | " + ((ImmediatePayment) obj).getId() + " |");
            } else if (obj instanceof Address) {
                System.out.println("| Address | " + AllObjInSys_obj.get(obj) + "  | " + ((Address) obj).getZipCode() + " |");
            }
            else if (obj instanceof Supplier) {
                System.out.println("| Supplier | " + AllObjInSys_obj.get(obj) + "  | " + ((Supplier) obj).getId() + " |"+((Supplier)obj).getName()+ " |");
            }
        }
    }

    public void Show_object_id(int id) {
        Object obj = AllObjInSys_id.get(id);
        System.out.println(obj.getClass().getName() + " ID: " + id);
        System.out.println(obj.toString());
    }

    public void Set_Supplier(String id, String name) {
        Supplier s=new Supplier(id,name);
        this.Suppliers.put(id, s);
        this.AllObjInSys_id.put(Static_Id,s);
        this.AllObjInSys_obj.put(s,Static_Id);
        Static_Id+=1;
    }

    public Supplier get_Supplier(String id) {
        return this.Suppliers.get(id);
    }

    public void Set_Product(String id, String name, Supplier s, int price) {
        Product p=new Product(id,name,s,price);
        this.Products.add(p);
        this.AllObjInSys_id.put(Static_Id,p);
        this.AllObjInSys_obj.put(p,Static_Id);
        Static_Id+=1;
    }

}



