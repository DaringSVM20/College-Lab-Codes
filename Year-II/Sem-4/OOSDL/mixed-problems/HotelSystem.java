/*
Design a HotelSystem<T extends Number> that stores Room<T> objects in an ArrayList. Each room has roomNo, type, price(T). Implement:
Add rooms, sort by price, display using Iterator.
Serialize the ArrayList to "hotel.dat".
Deserialize and print all rooms.
🧠 Architecture & Thought Process:
Room<T> must implements Serializable.
Generic class holds ArrayList<Room<T>>.
Use ObjectOutputStream/ObjectInputStream for the list itself (Java serializes collections automatically).
Handle exceptions cleanly.

*/

import java.io.*;
import java.util.*;

class Room<T extends Number> implements Serializable {
    int roomNo; String type; T price;
    public Room(int roomNo, String type, T price) {
        this.roomNo=roomNo; this.type=type; this.price=price;
    }
    public double getPriceDouble() { return price.doubleValue(); }
    public String toString() { return roomNo+" | "+type+" | $"+price; }
}

public class HotelSystem<T extends Number> implements Serializable {
    private ArrayList<Room<T>> rooms = new ArrayList<>();

    public void addRoom(Room<T> r) { rooms.add(r); }

    public void displayAndSort() {
        Collections.sort(rooms, Comparator.comparingDouble(Room::getPriceDouble));
        Iterator<Room<T>> it = rooms.iterator();
        while(it.hasNext()) System.out.println(it.next());
    }

    public void saveToFile(String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rooms);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            rooms = (ArrayList<Room<T>>) ois.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        HotelSystem<Double> sys = new HotelSystem<>();
        sys.addRoom(new Room<>(101, "Standard", 150.0));
        sys.addRoom(new Room<>(102, "Deluxe", 300.0));

        sys.saveToFile("hotel.dat");
        System.out.println("Saved.");

        sys.loadFromFile("hotel.dat");
        sys.displayAndSort();
    }
}