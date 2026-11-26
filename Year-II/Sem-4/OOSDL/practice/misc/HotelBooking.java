
// 3.	Design and implement a Java application to simulate a Hotel Room Booking System that demonstrates the object-oriented concepts of inheritance and runtime polymorphism.
// i.	Create a base class named Room that represents a general hotel room. The class should contain data members such as room number and base tariff, and a method calculateTariff() to compute the room cost.
// ii.	Create derived classes such as StandardRoom and LuxuryRoom that inherit from the Room class. Each derived class should override the calculateTariff() method to compute the tariff based on room-specific features such as air conditioning, additional amenities, or premium services.
// iii.	In the main class, create a base class reference of type Room and assign it to objects of different derived classes (StandardRoom, LuxuryRoom). Invoke the calculateTariff() method using the base class reference to demonstrate runtime polymorphism, where the method call is resolved at runtime based on the actual object type.


class Room {
    private int roomNo;
    private double baseTariff;

    public Room(int roomNo, double baseTariff) {
        this.roomNo = roomNo;
        this.baseTariff = baseTariff;
    }

    // This method will be overridden
    public double calculateTariff() {
        return baseTariff;
    }
}

class StandardRoom extends Room {
    private boolean hasAC;

    public StandardRoom(int roomNo, double baseTariff, boolean hasAC) {
        super(roomNo, baseTariff);
        this.hasAC = hasAC;
    }

    @Override
    public double calculateTariff() {
        if (hasAC) {
            return super.calculateTariff() + 200;
        } else {
            return super.calculateTariff();
        }
    }
}

class LuxuryRoom extends Room {
    private boolean premiumLunch;

    public LuxuryRoom(int roomNo, double baseTariff, boolean premiumLunch) {
        super(roomNo, baseTariff);
        this.premiumLunch = premiumLunch;
    }

    @Override
    public double calculateTariff() {
        if (premiumLunch) {
            return super.calculateTariff() + 500;
        } else {
            return super.calculateTariff();
        }
    }
}

public class HotelBooking {
    
    public static void main(String[] args) {
        Room r1;
        
        r1 = new StandardRoom(101, 1000, true);
        System.out.println("Standard Room Tariff: " + r1.calculateTariff());
        System.out.println("-----------------------------");

        r1 = new LuxuryRoom(102, 1200, true);
        System.out.println("Luxury Room Tariff: " + r1.calculateTariff());
        System.out.println("-----------------------------");
    }

}
