// 1.	Design and implement a Java application to simulate a Hotel Room Service Management System where multiple service requests are handled concurrently using multithreading.
// i.	In a hotel, different room service tasks such as room cleaning, food delivery, and maintenance may occur at the same time. To efficiently manage these tasks, the application should create separate threads for each service request so that they can execute concurrently rather than sequentially.
// ii.	Create individual threads for different service operations using Java thread creation techniques (Thread class or Runnable interface). Each thread should simulate a service task by displaying status messages and pausing execution using the sleep() method to represent processing time.
// iii.	The main program should start multiple threads simultaneously and demonstrate concurrent execution of hotel service tasks.

// package Q1;

class HotelService implements Runnable {

    private String serviceName;

    HotelService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        for (int i=0; i<=5; i++) {
            System.out.println(serviceName + " - Step: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(serviceName + " interrupted.");
            }
        }
        System.out.println(serviceName + " completed.");
    }
}

public class HotelServiceManagement {
    public static void main(String[] args) {
        
        Thread cleaning = new Thread(new HotelService("Room Cleaning"));
        Thread food = new Thread(new HotelService("Food Delivery"));
        Thread maintenance = new Thread(new HotelService("Maintenance  "));

        cleaning.start();
        food.start();
        maintenance.start();

    }
}