// Q5

class Pair<T, U> {
    T roomNo;
    U guest;

    Pair(T roomNo, U guest) {
        this.roomNo = roomNo;
        this.guest = guest;
    }

    void display() {
        System.out.println("Room: " + roomNo + " Guest: " + guest);
    }
}

public class BookingPairDemo {
    public static void main(String[] args) {
        Pair<Integer, String> b1 = new Pair<>(101, "Amit");
        Pair<Integer, String> b2 = new Pair<>(102, "Neha");

        b1.display();
        b2.display();
    }
}