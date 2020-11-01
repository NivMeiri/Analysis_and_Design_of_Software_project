import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {
        System1 s=new System1();
        //if(args[0]=="login"){s.login()};
        System.out.println("Hello World!");
        System.out.println("hey team");
        Supplier s=new Supplier("312","nim");
        Product p=new Product("111","CoffeMachine");
        p.setSupplier(s);
        Order o = new Order("8765", Date.valueOf("2015-03-30"), Date.valueOf("2015-03-30"), new Address(), OrderStatus.Hold, 9999);
        LineItem l=new LineItem(100,9999,o,p,);
        p.setSupplier(s);
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());
        System.out.println(o.toString());

        /*
        public void  Add(String Login_id){

        }
        */
        public void MakeOrder(){
            Scanner sciny=new Scanner(System.in);
            System.out.println("Who would you to order from?: ");
            String name=sciny.nextLine();
            WebUser MyWeb =this.WebUsers[name];//using the hashmap
            MyWeb.getShoppingCart().printProducts();
            System.out.println("Would you like to order one of our products? (y/n)");
            String ans = sciny.nextLine();
            while (ans != "n"){
                System.out.println("What product do you want to add to your Cart?: ");
                String item = sciny.nextLine();
                int index=-1;
                int degel=1;
                while (degel==1) {
                    for (int i = 0; i < MyWeb.getShoppingCart().getLineItems().size(); i++) {
                        if (MyWeb.getShoppingCart().getLineItems().get(i).getProduct().getName().equals(item)) {
                            index = i;
                            degel=0;
                            break;
                        }
                    }
                    if (index == -1)
                        System.out.println("Please enter valid name!");
                }




                }

            }

        }

    }

}

