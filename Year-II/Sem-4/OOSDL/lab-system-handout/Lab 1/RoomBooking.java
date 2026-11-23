
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
