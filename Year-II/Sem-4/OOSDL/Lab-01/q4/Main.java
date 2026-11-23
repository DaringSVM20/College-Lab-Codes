interface Amenities {
    void provideWifi();
    void provideBreakfast();
}

abstract class Room {
    int roomNo;
    double basePrice;

    Room(int roomNo, double basePrice) {
        this.roomNo = roomNo;
        this.basePrice = basePrice;
    }

    abstract double calculateTariff();

    void displayRoomDetails() {
        System.out.println("Room No: " + roomNo + ", Base Price: " + basePrice);
    }
}

class StandardRoom extends Room implements Amenities {

    StandardRoom(int roomNo, double basePrice) {
        super(roomNo, basePrice);
    }

    double calculateTariff() {
        return basePrice * 1.1;  // 10% extra
    }

    public void provideWifi() { System.out.println("Wifi: Yes"); }
    public void provideBreakfast() { System.out.println("Breakfast: No"); }
}

class LuxuryRoom extends Room implements Amenities {

    LuxuryRoom(int roomNo, double basePrice) {
        super(roomNo, basePrice);
    }

    double calculateTariff() {
        return basePrice * 1.5;  // 50% extra
    }

    public void provideWifi() { System.out.println("Wifi: Yes"); }
    public void provideBreakfast() { System.out.println("Breakfast: Yes"); }
}

public class Main {
    public static void main(String[] args) {

        Room r;  // base class reference

        r = new StandardRoom(101, 2000);
        r.displayRoomDetails();
        System.out.println("Tariff: " + r.calculateTariff());
        ((Amenities) r).provideWifi();
        ((Amenities) r).provideBreakfast();

        System.out.println();

        r = new LuxuryRoom(201, 3000);
        r.displayRoomDetails();
        System.out.println("Tariff: " + r.calculateTariff());
        ((Amenities) r).provideWifi();
        ((Amenities) r).provideBreakfast();
    }
}
