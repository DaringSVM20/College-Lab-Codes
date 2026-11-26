package Lab1Q2;
// 2.	Create a base class named Room to represent general room details in a hotel. The class should contain data members such as room number, room type, and base price. Implement multiple constructors (constructor overloading) in the Room class to initialize room objects in different ways, such as:
// i.	Initializing only the room number and type
// ii.	Initializing room number, type, and base price
// iii.	Create a derived class named DeluxeRoom that inherits from the Room class using single inheritance. The derived class should include additional data members such as free Wi-Fi availability and complimentary breakfast. Implement appropriate constructors in the derived class that invoke the base class constructors using the super keyword.
// iv.	Create a main class to instantiate objects of both Room and DeluxeRoom using different constructors and display the room details. This application should clearly illustrate constructor overloading and inheritance.


class Room {

    private int roomNo;
    private String roomType;
    private double basePrice;
    // Default constructor
    public Room(int roomNo, String roomType) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.basePrice = 1500.0;
    }
    // Second constructor for number, type and base price
    public Room(int roomNo, String roomType, double basePrice) {
        this.roomNo = roomNo;
        this.roomType = roomType;
       this.basePrice = basePrice;
    }
    // Method for displaying details
    public void display() {
        System.out.println("Room No: " + roomNo);
        System.out.println("Room Type: " + roomType);
        System.out.println("Base Price: " + basePrice);
    }

}


