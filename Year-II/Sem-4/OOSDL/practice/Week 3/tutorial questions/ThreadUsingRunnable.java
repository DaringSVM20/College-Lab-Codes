// Threads using Runnable interface

class roomCleaning implements Runnable {

    private String roomName;

    roomCleaning(String roomName) {
        this.roomName = roomName;
    }

    // Now every time we need to do some thread business, we do so inside the class where the object is gonna be the thread, for instance here in this code, since we implemented runnable in this class hence we will also override the run method in this class only. Also the run method always required to make a try-catch statement for the imitation of what an actual code would do, which we simulate using Thread.sleep() method.

    @Override
    public void run() {
        for (int i=0; i<= 3; i++) {
            System.out.println(roomName + "- Cleaning step: " + i);
            try {
                java.lang.Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}

// Main class

public class ThreadUsingRunnable {
    public static void main(String[] args) {

        roomCleaning t1 = new roomCleaning("Room A");
        roomCleaning t2 = new roomCleaning("Room B");

        java.lang.Thread thread1 = new java.lang.Thread(t1);
        java.lang.Thread thread2 = new java.lang.Thread(t2);

        thread1.start();
        thread2.start();
    }
}