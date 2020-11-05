import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {
        System1 s1 = new System1();
        s1.Set_Supplier("123","Moshe");
        s1.Set_Product("Ramen","Ramen",s1.get_Supplier("123"),100);
        s1.Set_Product("Bamba","Bamba",s1.get_Supplier("123"),89);
        ///todo add manual Constructor to WebUser
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter command: ....if you want to stop enter Exit");
            String msg = in.nextLine();
            String[] splited = msg.split("\\s+");
            int Length = splited.length;
            String First_Command,Second_Command="",Third_input="";
            First_Command = splited[0];
            if(Length>1) {
                Second_Command = splited[1];
            }
            if(Length>2) {
                Third_input = splited[2];
            }
            switch (Length) {
                case 1:
                    if (First_Command.equals("ShowAllObjects")) {
                        s1.Show_all_Objects();
                        break;
                    }
                    if (First_Command.equals("ShowObjectId")) {
                        s1.Show_object_id(Integer.parseInt(Third_input));
                        break;
                    }
                    if (First_Command.equals("Exit")) {
                        System.out.println("Goodbye!");
                        return;
                    }

                case 2:
                    if (First_Command.equals("LogOut") && Second_Command.equals("WebUser"))
                        s1.LogOut();
                    if (First_Command.equals("Make") && Second_Command.equals("order"))
                        s1.LogOut();
                    if (First_Command.equals("Display") && Second_Command.equals("order"))
                        s1.Display_Order();
                    if (First_Command.equals("Add") && Second_Command.equals("Product"))
                        s1.AddProduct();
                case 3:
                    if (First_Command.equals("Add") && Second_Command.equals("WebUser")) {
                        s1.Add_WebUser(Third_input);
                        break;
                    }
                    if (First_Command.equals("Remove") && Second_Command.equals("WebUser")) {
                        s1.Remove_Webuser(Third_input);
                        break;
                    }
                    if (First_Command.equals("Login") && Second_Command.equals("WebUser")) {
                        s1.Login(Third_input);
                        break;
                    }
                    if (First_Command.equals("Link") && Second_Command.equals("Product")) {
                        s1.Link_Product(Third_input);
                        break;
                    }
                    if (First_Command.equals("Delete") && Second_Command.equals("Product")) {
                        s1.Delete_Product(Third_input);
                        break;
                    }
                default:
                    System.out.println("Your Input is Incorrect!!Please try again");
            }
        }
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

