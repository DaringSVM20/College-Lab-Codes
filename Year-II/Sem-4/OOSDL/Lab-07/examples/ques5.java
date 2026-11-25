/*
5. Develop a hotel room booking module that uses a generic pair class to associate room
numbers with guest details.
• Create a generic class Pair<T, U>
• Store:
o Room Number (Integer)
o Guest Name (String)
• Display booking records
• Ensure no type casting is required

*/

class Pair<T, U> {
    T roomNo;
    U guest;

    Pair(T roomNo, U guest) {
        this.roomNo = roomNo;
        this.guest = guest;
    }

    void display() {
        System.out.println("Room: " + roomNo + " | Guest: " + guest);
    }
}

public class ques5 {
    public static void main(String[] args) {
        Pair<Integer,String>p1 = new Pair<>(101, "Allen");
        Pair<Integer,String>p2 = new Pair<>(102, "Zoe");

        p1.display();
        p2.display();
    }
}
