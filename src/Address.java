public class Address {

    private String Add;
    private String street;

    private int streetNumber;
    private String city;
    private String state;
    private long zipCode;



    public Address(String street, int streetNumber, String city, String state, long zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public Address(String address) {
        this.Add = address;
    }
    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString(){
        String address = "[" + streetNumber + ", " + street + ", " + city + ", " + state + ", " + zipCode+"]";
        return address;
    }
}