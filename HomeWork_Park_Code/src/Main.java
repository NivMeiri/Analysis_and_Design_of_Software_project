import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static public   List<Object> systemObjects=new ArrayList<Object>();
    static Scanner scan = new Scanner(System.in);
    static List<Device> DeviceList = new ArrayList<Device>();
    static ChildController childCont = new ChildController();
    static  GuardianController GuardCont = new GuardianController();
    public static void main(String[] args) throws Exception {

        ///todo add manual Constructor to WebUser

        GuardCont.childControll = childCont;
        Device D1 = new Device(true, 1.4, 0,12, "Mamba Ride");
        Device D2 = new Device(false, 0, 0, 0,"Giant Wheel");
        Device D3 = new Device(true, 0, 0, 8,"Carrousel");
        systemObjects.add(D1);
        systemObjects.add(D2);
        systemObjects.add(D3);
        DeviceList.add(D1);
        DeviceList.add(D2);
        DeviceList.add(D3);
        systemObjects.add(childCont);
        systemObjects.add(GuardCont);

        boolean flag = true;
        while (flag == true) {
            System.out.println("\nWelcome to the e-Park Application : \n" +
                    "enter 1 : Guardian Sign in to account that already exist \n" +
                    "enter 2:  Register new Guardian to the app  \n" +
                    "enter 3:  Exit\n");
            String choice = scan.nextLine();
            switch (choice) {
                case "1": {
                    System.out.println("welcome back back , please enter your id  [str]:");
                    String id = scan.nextLine();
                    System.out.println("Please enter your  Password   [int]:");
                    String pass = scan.nextLine();
                    int Password = Integer.parseInt(pass);
                    Guardian guardian = GuardCont.CheckGuardDetails(id, Password);
                    if (guardian == null) {
                        break;
                    }
                    System.out.println("You are in!!");
                    boolean flag2 = true;
                    while (flag2 == true) {
                        System.out.println("\nPlease enter a number between 1-6 : \n" +
                                "enter 1 : Register child \n" +
                                "enter 2:  Manage ticket\n" +
                                "enter 3:  Exit park\n" +
                                "enter 4:  Exit\n");
                        choice = scan.nextLine();
                        switch (choice) {
                            case "1":
                                System.out.println("Register child");
                                System.out.println("Please enter name  [string]:");
                                String name = scan.nextLine();
                                System.out.println("Please enter Age  [int]:");
                                String AgeStr = scan.nextLine();
                                int Age = Integer.parseInt(AgeStr);
                                System.out.println("Please enter Height  [float]:");
                                String HeightStr = scan.nextLine();
                                double Height = Double.parseDouble(HeightStr);
                                System.out.println("Please enter Weight  [float]:");
                                String WeightStr = scan.nextLine();
                                double Weight = Double.parseDouble(WeightStr);
                                System.out.println("Please enter limit time  [int]:");
                                String timeStr = scan.nextLine();
                                int time = Integer.parseInt(timeStr);
                                GuardCont.addChild(Age, name, Weight, Height,time, guardian);
                                break;
                            case "2":
                                System.out.println("what is the id of the child that you want's to edit ride:  [Str]");
                                String idChildStr = scan.nextLine();
                                int idChild = Integer.parseInt(idChildStr);
                                Child child = guardian.findChild(idChild);
                                if (child == null) {
                                    break;
                                }
                                boolean flag3 = true;
                                while (flag3 == true) {
                                    System.out.println("\nWhat do you want to do? : \n" +
                                            "enter 1 : Add Entries \n" +
                                            "enter 2:  Remove Entries\n" +
                                            "enter 3:  Exit park\n" +
                                            "enter 4:  Exit\n");
                                    choice = scan.nextLine();
                                    switch (choice) {
                                        case "1": {
                                            AddEntry(guardian,child);
                                            break;
                                        }
                                        case "2": {
                                            DeleteEntry(guardian,child);
                                            break;
                                        }
                                        case "3":{
                                            ExitPark(guardian,child);
                                            System.out.println("The child was removed!");
                                            break;
                                        }
                                        case "4":
                                            System.out.println("goodbye!");
                                            deleteAllObject();
                                            flag = false;
                                            break;
                                    }
                                }
                                break;
                            case "3":
                                System.out.println("Enter a string: ");
                                String word1 = scan.nextLine();
                                System.out.println("Enter an integer: ");
                                int numberOfTimes = scan.nextInt();
                                System.out.println("Enter the third number");
                                break;
                            case "4":
                                System.out.println("goodbye!");
                                deleteAllObject();
                                flag = false;
                                break;
                            default:
                                System.out.println("you need to insert valid number!");
                        }
                    }
                    break;
                }
                case "2": {
                    System.out.println("This is the register process");
                    System.out.println("Please enter your id:   [string]");
                    String id = scan.nextLine();
                    System.out.println("Please enter Password       [int]");
                    String pass1 = scan.nextLine();
                    int pass = Integer.parseInt(pass1);
                    Guardian g = GuardCont.createGuardian(id, pass);
                    systemObjects.add(g);
                    System.out.println("Credit card num   [int ]");
                    String ccnum1 = scan.nextLine();
                    int ccnum = Integer.parseInt(ccnum1);
                    System.out.println("Please enter your account's limit    [int]");
                    String lim1 = scan.nextLine();
                    int lim = Integer.parseInt(lim1);
                    double Moneyleft = 10000;
                    System.out.println("The Bank is checking the details, please wait...");
                    GuardCont.checkCCNum(ccnum, Moneyleft, lim, g);
                    break;
                }
                case "3":
                    System.out.println("goodbye!");
                    deleteAllObject();
                    flag = false;
                    break;

            }
        }
    }

    public  static void AddEntry(Guardian g, Child c){
        System.out.println("this is the list of the relevant devices for this child");
        for (Device d: DeviceList){
            if(d.canGoOn(c.e,c))
                System.out.println("device :" +d.name);
        }
        System.out.println("insert the name of the device that you want to add ride");
        String device1 = scan.nextLine();
        Device device=null;
        for (Device d: DeviceList){
            if(d.name.equals(device1))
                device = d;
        }
        if(device.isExtreme){
            System.out.println("this is extreme device, do you approve the entry? yes/no");
            String yes_no=scan.nextLine().toLowerCase();
            if(yes_no.equals("no")){
                System.out.println("you didnt approve, addEntry cancelled");
            }
        }

        GuardCont.AddEntry(g,c,device);
        System.out.println(" this device was added successfully: "+ device.name);
    }

    public static void DeleteEntry(Guardian g, Child c){
        System.out.println("Choose the match number for the device that you want to remove:");
        int num=0;
        for (Entry e: c.e.entries){
            System.out.println("choose "+num+": for "+e.d.name);
            num++;
        }
        String number1=scan.nextLine();
        int number=Integer.parseInt(number1);
        if (number>=c.e.entries.size() || number<0){
            System.out.println("invalid number");
            return;
        }
        num=0;
        Entry toDel=null;
        for (Entry e: c.e.entries){
            System.out.println("choose "+num+": for "+e.d.name);
            if(number==num)
                toDel=e;
            else
                num++;
        }
        GuardCont.DeleteEntry(toDel,c);


    }
    public static void ExitPark(Guardian guardian,Child child){
        GuardCont.deleteChild(child,guardian);
    }
    public static void deleteAllObject(){
        systemObjects=null;
        GuardCont=null;
        childCont=null;
        DeviceList=null;
    }

}
