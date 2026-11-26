// Design and implement a Java-based hotel room management application that simulates concurrent room booking and room release operations using multiple threads. The system must ensure data consistency when multiple customers attempt to book or release rooms simultaneously. A hotel has a limited number of rooms. Multiple customer threads attempt to book rooms at the same time. If no rooms are available, the booking thread must wait. When a room is released by another thread, the waiting booking thread must be notified and allowed to proceed.

// Must use - 1) synchronized 2) wait() 3) notify() methods.

class Hotel {

    private int availableRooms;

    Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    // Booking a room
    synchronized void bookRoom(String customer) {

        while(availableRooms == 0) {
            System.out.println(customer + " is waiting to book a room. No rooms available.");
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        availableRooms--;
        System.out.println(customer + " has booked a room. Rooms left: " + availableRooms);

    }
    
    
    // Releasing a booked room
    synchronized void releaseRoom(String customer) {

        availableRooms++;
        System.out.println(customer + " has released a room. Rooms available: " + availableRooms);
        notify();

    }

}

class Customer extends Thread {

    private Hotel hotel;
    private String customerName;
    private boolean booking; // true for booking, false for releasing

    Customer(Hotel h, String custName, boolean booking) {
        this.hotel = h;
        this.customerName = custName;
        this.booking = booking;
    }

    @Override
    public void run() {
        if (booking) {
            hotel.bookRoom(customerName);
        } else {
            try {
                Thread.sleep(5000); // Simulate time taken to use the room before releasing
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            hotel.releaseRoom(customerName);
        }
    }

}

public class HotelRoom {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(1); // Hotel with only one room

        Customer c1 = new Customer(hotel, "Guy 1", true); // First guy tries to book
        Customer c2 = new Customer(hotel, "Guy 2", true); // Second guy tries to book, but can't
        Customer c3 = new Customer(hotel, "Guy 1", false); // First guy releases the room, second gets it
        
        c1.start();
        c2.start();
        c3.start();
        
    }
}
