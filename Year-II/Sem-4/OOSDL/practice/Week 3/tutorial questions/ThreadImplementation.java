// package Week 3.tutorial questions;

class roomCleaning extends java.lang.Thread {
    private String roomName;

    public roomCleaning(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public void run() {
        for (int i=1; i<=5; i++) {
            System.out.println(roomName + "- Cleaning step" + i);
            try {
                java.lang.Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Thread Interrupted: " + e.getMessage());
            }
        }
    }
    

}

public class ThreadImplementation {
    public static void main(String[] args) {
        roomCleaning t1 = new roomCleaning("Room A");
        roomCleaning t2 = new roomCleaning("Room B");
        
        t1.start();
        t2.start();
        
    }
}
