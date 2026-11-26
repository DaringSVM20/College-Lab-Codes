package Lab1Q2;
// 2.	Create a base class named Room to represent general room details in a hotel. The class should contain data members such as room number, room type, and base price. Implement multiple constructors (constructor overloading) in the Room class to initialize room objects in different ways, such as:
// i.	Initializing only the room number and type
// ii.	Initializing room number, type, and base price
// iii.	Create a derived class named DeluxeRoom that inherits from the Room class using single inheritance. The derived class should include additional data members such as free Wi-Fi availability and complimentary breakfast. Implement appropriate constructors in the derived class that invoke the base class constructors using the super keyword.
// iv.	Create a main class to instantiate objects of both Room and DeluxeRoom using different constructors and display the room details. This application should clearly illustrate constructor overloading and inheritance.


public class HotelApp {
    
    public static void main(String[] args) {
        Room r1 = new Room(
            101, 
            "Standard"
        );
        r1.display();
        System.out.println();

        Room r2 = new Room(
            102, 
            "Deluxe", 
            3500.0
        );
        r2.display();
        System.out.println();

        DeluxeRoom dr1 = new DeluxeRoom(
            103, 
            "Deluxe", 
            true, 
            false
        );
        dr1.display();
        System.out.println();

        DeluxeRoom dr2 = new DeluxeRoom(
            104, 
            "Luxury Deluxe", 
            6500.0, 
            true, 
            true
        );
        dr2.display();
        System.out.println();
    }

}
