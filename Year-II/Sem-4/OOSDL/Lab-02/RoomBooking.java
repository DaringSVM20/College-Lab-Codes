// package Lab 2;
// 2.	Design and implement a Java application to manage room tariff details in a Hotel Management System using Java enumerations (enum). The application should demonstrate the use of enum constants, enum constructors, and enum methods.
// i.	Define an enum named RoomType to represent different types of hotel rooms such as STANDARD, DELUXE, and SUITE. Each enum constant should be associated with a base tariff value using an enum constructor. The enum should also include methods to return the base tariff and to calculate the total room cost based on the number of days stayed.
// ii.	Create a main class to select a room type, specify the number of days of stay, and compute the total room tariff by invoking the enum methods. The application should clearly illustrate how enum constructors are used to initialize constant-specific data and how enum methods operate on that data.


enum RoomType {

    STANDARD(2000.0),
    DELUXE(3500.0),
    SUITE(5000.0);

    private double baseTariff;

    RoomType(double baseTariff) {
        this.baseTariff = baseTariff;
    }

    public double getBaseTariff() {
        return baseTariff;
    }

    public double calcTotalCost(int days) {
        return baseTariff * days;
    }

}

public class RoomBooking {
    
    public static void main(String[] args) {
        
        RoomType r1 = RoomType.SUITE;

        int days = 5;

        double totalCost = r1.calcTotalCost(days);

        System.out.println("Hotel Management System");
        System.out.println("-----------------------");
        System.out.println("Selected Room Type: " + r1);
        System.out.println("Base Tariff (per day): " + r1.getBaseTariff());
        System.out.println("Number of Days Stayed: " + days);
        System.out.println("Total Room Tariff: " + totalCost);
    }

}
