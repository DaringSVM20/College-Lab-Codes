// Enum definition
enum RoomType {
    // Enum constants with base tariff values
    STANDARD(2000.0),
    DELUXE(3500.0),
    SUITE(5000.0);

    // Instance variable to store base tariff
    private double baseTariff;

    // Enum constructor (called once per constant)
    RoomType(double baseTariff) {
        this.baseTariff = baseTariff;
    }

    // Method to return base tariff
    public double getBaseTariff() {
        return baseTariff;
    }

    // Method to calculate total cost based on days stayed
    public double calculateTotalCost(int days) {
        return baseTariff * days;
    }
}

// Main class
public class HotelManagementSystem {
    public static void main(String[] args) {
        // Select a room type (enum constant)
        RoomType selectedRoom = RoomType.DELUXE;

        // Specify number of days stayed
        int daysStayed = 4;

        // Compute total tariff using enum methods
        double totalCost = selectedRoom.calculateTotalCost(daysStayed);

        // Display results
        System.out.println("Hotel Management System");
        System.out.println("-----------------------");
        System.out.println("Selected Room Type: " + selectedRoom);
        System.out.println("Base Tariff (per day): " + selectedRoom.getBaseTariff());
        System.out.println("Number of Days Stayed: " + daysStayed);
        System.out.println("Total Room Tariff: " + totalCost);
    }
}

