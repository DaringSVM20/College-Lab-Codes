
class buffer {
    private int data;
    private boolean avail = false;

    // Method to produce data
    synchronized void produce(int value) {

        while (avail) {
            try {
                wait(); // Wait if the buffer is full.
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        data = value;
        avail = true;
        System.out.println("Produced: " + data);
        
        notify();
    }
    

    synchronized int consume() {

        while (!avail) {
            try {
                wait(); // Wait till the buffer is empty.
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        avail = false;
        System.out.println("Consumed: " + data);

        notify();
        return data;
    }

}

class Producer extends Thread {

    private buffer b;

    Producer (buffer b) {
        this.b = b;
    }

    @Override
    public void run() {

        for (int i=1; i<=3; i++) {
            b.produce(i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }  

}

class Consumer extends Thread {

    private buffer b;

    Consumer (buffer b) {
        this.b = b;
    }

    @Override
    public void run() {

        for (int i=1; i<=3; i++) {
            b.consume();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

}


public class ProducerConsumer {
    
    public static void main(String[] args) {
        buffer b = new buffer();

        Producer p = new Producer(b);
        Consumer c = new Consumer(b);

        p.start();
        c.start();

    }

}
