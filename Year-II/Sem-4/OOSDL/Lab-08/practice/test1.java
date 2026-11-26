// Q: Manage rooms using ArrayList. Add rooms, remove available ones under ₹3000, sort by price, iterate safely.
import java.util.*;

class Room {
    int no;
    String type;
    double price;
    boolean avail;

    Room(int n, String t, double p, boolean a) {
        this.no=n;
        this.type=t;
        this.price=p;
        this.avail=a;
    }
}

public class test1 {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Single", 2500.0, true));
        rooms.add(new Room(102, "Deluxe", 4500.0, false));

        // rooms.removeIf(r -> r.avail && price < 3000.0)
        rooms.removeIf(r -> r.avail && r.price < 3000.0);

        Collections.sort(rooms, Comparator.comparingDouble((Room r) -> r.price).reversed());

    }
}
