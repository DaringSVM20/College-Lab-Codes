package Lab1Q2;
// 2.	Create a base class named Room to represent general room details in a hotel. The class should contain data members such as room number, room type, and base price. Implement multiple constructors (constructor overloading) in the Room class to initialize room objects in different ways, such as:
// i.	Initializing only the room number and type
// ii.	Initializing room number, type, and base price
// iii.	Create a derived class named DeluxeRoom that inherits from the Room class using single inheritance. The derived class should include additional data members such as free Wi-Fi availability and complimentary breakfast. Implement appropriate constructors in the derived class that invoke the base class constructors using the super keyword.
// iv.	Create a main class to instantiate objects of both Room and DeluxeRoom using different constructors and display the room details. This application should clearly illustrate constructor overloading and inheritance.


class DeluxeRoom extends Room {
    private boolean freeWifi;
    private boolean compBrkFast;

    // Constructor for subclass without basePrice
    public DeluxeRoom(int roomNo, String roomType, boolean freeWifi, boolean compBrkFast) {
        super(roomNo, roomType, 2000.0);
        this.freeWifi = freeWifi;
        this.compBrkFast = compBrkFast;
    }

    // Constructor with basePrice
    public DeluxeRoom(int roomNo, String roomType, double basePrice, boolean freeWifi, boolean compBrkFast) {
        super(roomNo, roomType, basePrice);
        this.freeWifi = freeWifi;
        this.compBrkFast = compBrkFast;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Free Wi-Fi: " + (freeWifi ? "Yes" : "No"));
        System.out.println("Complimentary Breakfast: " + (compBrkFast ? "Yes" : "No"));
    }

}
