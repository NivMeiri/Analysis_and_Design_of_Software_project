import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {

        System1 s1=new System1();
        s1.AddProduct();
        s1.Add_WebUser("GuyGlo");///todo add args[2] input from cmd
        s1.Login("1234");
        //if(args[0]=="login"){s.login()};
        Supplier s=new Supplier("312","nim");
        Product p=new Product("111","CoffeMachine",s);
        p.setSupplier(s);
        s1.Add_WebUser("1234");
        s1.AddProduct();
        s1.Login("1234");
        s1.AddProduct();
        s1.MakeOrder();
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());
        }
    }



