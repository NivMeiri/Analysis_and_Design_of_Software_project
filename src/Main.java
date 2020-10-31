import java.sql.Date;
import java.util.List;

public class Main {
    private List<WebUser> WebUsers;
    private List<Product> Products;


    public static void main(String[] args) {

        System.out.println("Hello World!");
        System.out.println("hey team");
        Supplier s=new Supplier("312","nim");
        Product p=new Product("111","CoffeMachine");
        p.setSupplier(s);
        Order o = new Order("8765", Date.valueOf("2015-03-30"), Date.valueOf("2015-03-30"), new Address(), OrderStatus.Hold, 9999);
        //LineItem l=new LineItem(100,9999,o,p);
        p.setSupplier(s);
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());
        System.out.println(o.toString());

        /*
        public void  Add(String Login_id){

        }
        */

    }

}
