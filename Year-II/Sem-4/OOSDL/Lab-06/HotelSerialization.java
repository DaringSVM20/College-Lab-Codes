// Question 2

import java.io.*;
import java.util.*;

// Room class must be Serializable
class Room implements Serializable {
    int roomNo;
    String type;
    double price;
    boolean booked;
    String guestName;

    Room(int roomNo, String type, double price, boolean booked, String guestName) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        this.booked = booked;
        this.guestName = guestName;
    }

    public String toString() {
        return roomNo + " | " + type + " | Rs." + price +
               " | Booked: " + booked + " | Guest: " + guestName;
    }
}

public class HotelSerialization {

    static final String FILE = "rooms.ser";

    // Serialize list of rooms
    static void saveRooms(List<Room> list) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
        oos.writeObject(list);
        oos.close();
    }

    // Deserialize list of rooms
    static List<Room> loadRooms() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
            List<Room> list = (List<Room>) ois.readObject();
            ois.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<>(); // file empty or not found
        }
    }

    // Display all rooms
    static void displayAll() {
        List<Room> list = loadRooms();
        System.out.println("\n--- All Rooms ---");
        for (Room r : list)
            System.out.println(r);
    }

    // Search by room number
    static void searchRoom(int roomNo) {
        List<Room> list = loadRooms();
        for (Room r : list) {
            if (r.roomNo == roomNo) {
                System.out.println("\nFound: " + r);
                return;
            }
        }
        System.out.println("\nRoom not found!");
    }

    // Update booking status
    static void updateStatus(int roomNo, boolean status, String guest) throws Exception {
        List<Room> list = loadRooms();
        for (Room r : list) {
            if (r.roomNo == roomNo) {
                r.booked = status;
                r.guestName = guest;
                break;
            }
        }
        saveRooms(list);
        System.out.println("\nRoom " + roomNo + " updated.");
    }

    public static void main(String[] args) throws Exception {

        // Step 1: Hardcoded Room Objects
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Deluxe", 3000, false, "None"));
        rooms.add(new Room(102, "Suite", 5000, true, "Rahul"));
        rooms.add(new Room(103, "Standard", 1500, false, "None"));

        // Step 2: Serialize list
        saveRooms(rooms);
        System.out.println("Rooms Saved Successfully.");

        // Step 3: Display all
        displayAll();

        // Step 4: Search room
        searchRoom(102);

        // Step 5: Update booking (Room 103 booked by Meera)
        updateStatus(103, true, "Meera");

        // Step 6: Display again
        displayAll();
    }
}