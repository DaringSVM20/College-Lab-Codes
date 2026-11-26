

class serviceThread extends Thread {

    private String serviceName;

    public serviceThread(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        
        try {
            System.out.println(serviceName + " started.");

            for (int i=1; i<=3; i++) {
                System.out.println("Step " + i + " of " + serviceName);
                Thread.sleep(200);
            }

            System.out.println(serviceName + " completed.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

public class HotelManagement {

    public static void main(String[] args) {
        
        serviceThread cleaning = new serviceThread("Cleaning");
        serviceThread laundry = new serviceThread("Laundry");
        serviceThread food = new serviceThread("Food Delivery");

        cleaning.start();
        laundry.start();
        food.start();

        System.out.println("Hotel services are being processed...");

    }
    
}