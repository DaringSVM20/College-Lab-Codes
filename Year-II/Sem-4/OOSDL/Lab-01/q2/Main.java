class Room {
    int roomNo;
    String type;
    double price;

    Room(int roomNo, String type) {           // constructor 1
        this.roomNo = roomNo;
        this.type = type;
        this.price = 0;
    }

    Room(int roomNo, String type, double price) { // constructor 2
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println("Room No: " + roomNo +
                           ", Type: " + type +
                           ", Price: " + price);
    }
}

class DeluxeRoom extends Room {
    boolean wifi;
    boolean breakfast;

    DeluxeRoom(int roomNo, String type) {              // uses base constructor 1
        super(roomNo, type);
        this.wifi = true;
        this.breakfast = true;
    }

    DeluxeRoom(int roomNo, String type, double price) { // uses base constructor 2
        super(roomNo, type, price);
        this.wifi = true;
        this.breakfast = true;
    }

    void display() {
        super.display();
        System.out.println("WiFi: " + wifi + ", Breakfast: " + breakfast);
    }
}

public class Main {
    public static void main(String[] args) {

        Room r1 = new Room(101, "Standard");
        Room r2 = new Room(102, "Premium", 2500);

        DeluxeRoom d1 = new DeluxeRoom(201, "Deluxe");
        DeluxeRoom d2 = new DeluxeRoom(202, "Deluxe", 4500);

        r1.display();
        r2.display();
        d1.display();
        d2.display();
    }
}
