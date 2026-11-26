class orderThread extends Thread {

    private String orderId;

    public orderThread(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        try {
            
            System.out.println("Order " + orderId + ": Processing started.");
            sleep(500);
            
            System.out.println("Order " + orderId + ": Payment received. Processing....");
            sleep(500);
            
            System.out.println("Order " + orderId + ": Payment Confirmed. Order dispatched.");
            sleep(500);
            
            System.out.println("Order " + orderId + ": Order reached destination. Order Completed.");
            sleep(500);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

public class OrderProcessing {

    public static void main(String[] args) {
        
        orderThread t1 = new orderThread("ORD101");
        orderThread t2 = new orderThread("ORD102");
        orderThread t3 = new orderThread("ORD103");

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Online orders are being processed currently... ");

    }
    
}