import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {
        System1 s1=new System1();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter command: ");
        String msg = in.nextLine();
        /*
        String[] splited = msg.split("\\s+");
        String First_Command=splited[0];
        String Second_Command=splited[1];
        String input=splited[2];
        for (String s :splited)
            System.out.println(s);
         */
        switch (msg) {
            case "Add WebUser":  s1.Add_WebUser("1234");
                break;
            case "Remove WebUser":  s1.Remove_Webuser("1234");
                break;
            case "Login WebUser" :  s1.Login("1234");
                break;
            case"Logout WebUser": s1.LogOut();
                break;
            case "Make order":  s1.MakeOrder();
                break;
            case "Display order":s1.Display_Order();
                break;
            case "Link Product":  s1.Link_Product("1234");
                break;
            case "Add Product":  s1.AddProduct();
                break;
            case "Delete Product" : s1.Delete_Product("1234");
                break;
            case "ShowAllObjects": s1.Show_all_Objects();
                break;
            case "ShowObjectId":s1.Show_object_id(1234);
                break;

        }
        System.out.println("finish");
    }
}
/*
        System1 s1=new System1();
        s1.AddProduct();
        s1.Add_WebUser("Guy");///todo add args[2] input from cmd
        s1.Login("Guy");
        s1.AddProduct();
        s1.Link_Product("bamba");
        s1.LogOut();
        //if(args[0]=="login"){s.login()};
        Supplier s=new Supplier("312","nim");
        Product p=new Product("111","CoffeMachine",s);
        Customer newCustomer = new Customer("11",new Address(""),"","",null);
        Account NewAccount = new PremiumAccount("11","new addressi",false,new Date(2020,10,1),null,
                0,newCustomer,null);
        newCustomer.SetAccount(NewAccount);
        WebUser web=new WebUser("11","11",null,newCustomer);
        //p.setSupplier(s);
        s1.Add_WebUser("1234");
        //s1.AddProduct();
        s1.Login("1234");
        s1.MakeOrder();
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());
        }
    }
*/

