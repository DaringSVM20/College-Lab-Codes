/*
1. Develop a Java application that uses a generic class
 with two type parameters to store 
 hotel room information. 
 The generic class should be capable of holding 
 different data types for
 room identifiers and room attributes.
• Create a generic class Room<T, U>
• T represents Room Number or Room ID
• U represents Room Type or Price
• Demonstrate usage with different data types (e.g., Integer, String, Double)
• Display stored room details
*/

class Room<T,U> {
    T roomId;
    U detail;

    Room(T roomId, U detail) {
        this.roomId = roomId;
        this.detail = detail;
    }

    void display() {
        System.out.println("Room ID: " + roomId);
        System.out.println("Room Detail: " + detail);
    }
}

public class ques1 {
    public static void main(String[] args) {
        Room<Integer, String>r1 = new Room<>(101, "Deluxe");
        Room<String, Double>r2 = new Room<>("R202", 2500.0);

        r1.display();
        r2.display();
    }
}