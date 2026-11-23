class Room {
    int roomNo;
    double baseTariff;

    Room(int roomNo, double baseTariff) {
        this.roomNo = roomNo;
        this.baseTariff = baseTariff;
    }

    double calculateTariff() {
        return baseTariff;
    }
}

class StandardRoom extends Room {
    StandardRoom(int roomNo, double baseTariff) {
        super(roomNo, baseTariff);
    }

    double calculateTariff() {     // overridden
        return baseTariff * 1.1;   // 10% extra
    }
}

class LuxuryRoom extends Room {
    LuxuryRoom(int roomNo, double baseTariff) {
        super(roomNo, baseTariff);
    }

    double calculateTariff() {      // overridden
        return baseTariff * 1.5;    // 50% extra
    }
}

public class Main {
    public static void main(String[] args) {

        Room r;   // base class reference

        r = new StandardRoom(101, 2000);
        System.out.println("Standard: " + r.calculateTariff());

        r = new LuxuryRoom(201, 3000);
        System.out.println("Luxury: " + r.calculateTariff());
    }
}
