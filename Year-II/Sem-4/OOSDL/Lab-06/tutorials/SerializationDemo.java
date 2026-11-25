// package tutorials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    int roomNo; String type; double price; boolean booked;
    Room(int r, String t, double p, boolean b) { roomNo=r; type=t; price=p; booked=b; }
}

public class SerializationDemo {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Deluxe", 4500.0, false));
        rooms.add(new Room(102, "Standard", 2000.0, true));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rooms.dat"))) {
            oos.writeObject(rooms);
        } catch (IOException e) { e.printStackTrace(); }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rooms.dat"))) {
            List<Room> loaded = (List<Room>) ois.readObject();
            loaded.forEach(r -> System.out.printf("Room %d | %s | ₹ %.2f | %s%n",
                    r.roomNo, r.type, r.price, r.booked ? "Booked" : "Available"));
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }
}
