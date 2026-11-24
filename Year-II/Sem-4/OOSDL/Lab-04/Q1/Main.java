class Hotel {
    private int availableRooms;

    Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    synchronized void bookRoom(String customer) {
        try {
            while (availableRooms == 0) {
                System.out.println(customer + " waiting for room...");
                wait();   // wait until a room is released
            }
            availableRooms--;
            System.out.println(customer + " booked a room. Rooms left: " + availableRooms);
        } catch (Exception e) {}
    }

    synchronized void releaseRoom(String customer) {
        availableRooms++;
        System.out.println(customer + " released a room. Rooms left: " + availableRooms);
        notify();  // notify one waiting thread
    }
}

class BookingTask implements Runnable {
    Hotel h;
    String name;

    BookingTask(Hotel h, String name) {
        this.h = h;
        this.name = name;
    }

    public void run() {
        h.bookRoom(name);
    }
}

class ReleaseTask implements Runnable {
    Hotel h;
    String name;

    ReleaseTask(Hotel h, String name) {
        this.h = h;
        this.name = name;
    }

    public void run() {
        try { Thread.sleep(1000); } catch (Exception e) {}
        h.releaseRoom(name);
    }
}

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(1); // only 1 room

        Thread c1 = new Thread(new BookingTask(hotel, "Customer 1"));
        Thread c2 = new Thread(new BookingTask(hotel, "Customer 2"));
        Thread c3 = new Thread(new ReleaseTask(hotel, "Customer 3"));

        c1.start();
        c2.start();
        c3.start();
    }
}
