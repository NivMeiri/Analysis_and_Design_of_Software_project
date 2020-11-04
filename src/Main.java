import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {
        System1 s1=new System1();
        //s.Add_WebUser("9999");
        //if(args[0]=="login"){s.login()};
        System.out.println("Hello World!");
        System.out.println("hey team");
        Supplier s=new Supplier("312","nim");
        Product p=new Product("111","CoffeMachine",s);
        p.setSupplier(s);
        Order o = new Order();
        //LineItem l=new LineItem(100,9999,new ShoppingCart(null,null,null),o,p);
        p.setSupplier(s);
        s1.Add_WebUser("123");
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());
        System.out.println(o.toString());

        /*
        public void  Add(String Login_id){

        }
        */
        s1.MakeOrder();

        }

    }



