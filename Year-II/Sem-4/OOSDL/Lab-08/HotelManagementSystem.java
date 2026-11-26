import java.util.*;

// Room class
class Room {
    int roomNo;
    String type;
    double price;
    boolean available;

    Room(int roomNo, String type, double price) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        this.available = true;
    }
}

// Customer class
class Customer {
    int id;
    String name;
    String phone;
    int roomNo;

    Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.roomNo = -1;
    }
}

public class HotelManagementSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<Integer, Customer> bookings = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    // Add Room
    static void addRoom() {
        System.out.print("Enter Room No: ");
        int no = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        rooms.add(new Room(no, type, price));
        System.out.println("Room added.");
    }

    // Show Available Rooms
    static void showRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        Collections.sort(rooms, Comparator.comparingDouble(r -> r.price));

        System.out.println("Available Rooms:");
        for (Room r : rooms) {
            if (r.available) {
                System.out.println(r.roomNo + " | " + r.type + " | " + r.price);
            }
        }
    }

    // Add Customer
    static void addCustomer() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        customers.add(new Customer(id, name, phone));
        System.out.println("Customer added.");
    }

    // Find Room
    static Room findRoom(int no) {
        for (Room r : rooms)
            if (r.roomNo == no)
                return r;
        return null;
    }

    // Find Customer
    static Customer findCustomer(int id) {
        for (Customer c : customers)
            if (c.id == id)
                return c;
        return null;
    }

    // Book Room
    static void bookRoom() {
        System.out.print("Enter Room No: ");
        int rno = sc.nextInt();

        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();

        Room r = findRoom(rno);
        Customer c = findCustomer(cid);

        if (r == null || c == null) {
            System.out.println("Invalid data.");
            return;
        }

        if (!r.available) {
            System.out.println("Room already booked.");
            return;
        }

        r.available = false;
        c.roomNo = rno;
        bookings.put(rno, c);

        System.out.println("Room booked successfully.");
    }

    // Checkout
    static void checkout() {
        System.out.print("Enter Room No: ");
        int rno = sc.nextInt();

        if (!bookings.containsKey(rno)) {
            System.out.println("Room not booked.");
            return;
        }

        Room r = findRoom(rno);
        Customer c = bookings.get(rno);

        r.available = true;
        c.roomNo = -1;
        bookings.remove(rno);

        System.out.println("Checkout successful.");
    }

    // Show Customers using Iterator
    static void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }

        Iterator<Customer> it = customers.iterator();
        while (it.hasNext()) {
            Customer c = it.next();
            System.out.println(c.id + " | " + c.name + " | Room: " + c.roomNo);
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n--- HOTEL MENU ---");
            System.out.println("1. Add Room");
            System.out.println("2. Show Rooms");
            System.out.println("3. Add Customer");
            System.out.println("4. Book Room");
            System.out.println("5. Checkout");
            System.out.println("6. Show Customers");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addRoom(); break;
                case 2: showRooms(); break;
                case 3: addCustomer(); break;
                case 4: bookRoom(); break;
                case 5: checkout(); break;
                case 6: showCustomers(); break;
                case 7: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice");
            }

        } while (choice != 7);
    }
}