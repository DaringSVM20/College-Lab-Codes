// 1.	Create an abstract class named Room that represents a generic hotel room. The abstract class should contain common data members such as room number and base price, and include an abstract method calculateTariff() that must be implemented by all subclasses. It may also include concrete methods such as displayRoomDetails().
// i.	Create derived classes such as StandardRoom and LuxuryRoom that extend the abstract Room class and provide concrete implementations for the calculateTariff() method based on room-specific features.
// ii.	Create an interface named Amenities that declares methods such as provideWifi() and provideBreakfast(). The derived room classes should implement this interface to define the amenities offered for each room type.
// iii.	Create a main class to instantiate different room objects using a base class reference and invoke the implemented methods to demonstrate abstraction and interface-based design.

abstract class Room {
    int roomNo;
    double basePrice;

    Room(int roomNo, double basePrice) {
        this.roomNo = roomNo;
        this.basePrice = basePrice;
    }

    abstract double calculateTariff();

    void displayRoomDetails() {
        System.out.println("\nRoom Number: " + roomNo);
        System.out.println("Base Price: " + basePrice);
    }
}

interface amenities {
    void provideWifi();
    void provideBreakfast();
}

class StandardRoom extends Room implements amenities {
    
    StandardRoom(int roomNo, double basePrice) {
        super(roomNo, basePrice);
    }

    @Override
    double calculateTariff() {
        double tariff = basePrice + 500.0;
        System.out.println("Standard Room Tariff: " + tariff);
        return tariff;
    }

    public void provideWifi() {
        System.out.println("Standard WiFi provided.");
    }

    public void provideBreakfast() {
        System.out.println("Basic breakfast provided.");
    }
}

class LuxuryRoom extends Room implements amenities {

    LuxuryRoom(int roomNo, double basePrice) {
        super(roomNo, basePrice);
    }

    @Override
    double calculateTariff() {
        double tariff = basePrice + 2000.0;
        System.out.println("Luxury Room Tariff: " + tariff);
        return tariff;
    }

    public void provideWifi() {
        System.out.println("High-Speed WiFi provided.");
    }

    public void provideBreakfast() {
        System.out.println("Buffet breakast provided.");
    }
} 

public class Hotel {

    public static void main(String[] args) {
        Room r = new StandardRoom(101, 4500.0);
        r.displayRoomDetails();
        r.calculateTariff();

        amenities a = (amenities) r;
        a.provideWifi();
        a.provideBreakfast();

        r = new LuxuryRoom(102, 6000.0);
        r.displayRoomDetails();
        r.calculateTariff();

        a = (amenities) r;
        a.provideWifi();
        a.provideBreakfast();
    }
    
}
