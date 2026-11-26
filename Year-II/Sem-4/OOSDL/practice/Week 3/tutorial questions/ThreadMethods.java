class BookingThread extends Thread{
    
    private String taskName;

    BookingThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i=0; i<=3; i++) {
            System.out.println(taskName + " - Cleaning step: " + i);
            java.lang.Thread.yield();
            try {
                java.lang.Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println(taskName + " completed.");

    }

}


public class ThreadMethods {
    public static void main(String[] args) {
        BookingThread t1 = new BookingThread("Room Booking");
        BookingThread t2 = new BookingThread("Payment Processing");

        // Start first thread
        t1.start();

        try {
            // join() makes main thread wait till t1 completes.
            t1.join();
        } catch (Exception e) {
            System.err.println(e);
        }

        // Start second thread after first one finishes

        t2.start();

        try {
            t2.join();   // wait for second thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread Completed");


    }
}
