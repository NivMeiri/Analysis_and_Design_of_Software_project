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
        System.out.println(p.getSupplier().getId());
        System.out.println(p.getId());

        /*
        public void  Add(String Login_id){

        }
        */

    }

}
