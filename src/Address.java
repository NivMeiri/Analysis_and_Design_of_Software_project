public class Address {
    private String Add;

    public Address(String address) {
        this.Add = address;
    }

    @Override
    public String toString(){
        String address = "[" + Add +"]";
        return address;
    }
}