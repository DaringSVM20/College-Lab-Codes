class OrderTask implements Runnable {
    String orderName;

    OrderTask(String orderName) {
        this.orderName = orderName;
    }

    public void run() {
        try {
            System.out.println(orderName + " - Validation...");
            Thread.sleep(800);

            System.out.println(orderName + " - Payment Processing...");
            Thread.sleep(800);

            System.out.println(orderName + " - Shipment Initiated...");
            Thread.sleep(800);

            System.out.println(orderName + " - Completed!\n");
        } catch (Exception e) {}
    }
}

public class OrderProcessingSystem {
    public static void main(String[] args) {

        Thread o1 = new Thread(new OrderTask("Order #101"));
        Thread o2 = new Thread(new OrderTask("Order #102"));
        Thread o3 = new Thread(new OrderTask("Order #103"));

        o1.start();
        o2.start();
        o3.start();
    }
}
