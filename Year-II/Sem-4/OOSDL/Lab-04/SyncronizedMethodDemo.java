// Sample exercise

class BankAccount {

    private int bal = 1000;

    // Syncronized method
    synchronized void withdraw(int amt) {
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw " + amt);

        if (bal >= amt) {
            System.out.println(Thread.currentThread().getName() + " is processing withdrawal...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
            bal -= amt;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + bal);

        } else {
            System.out.println(Thread.currentThread().getName() + " Insufficient balance!");
        }
    }
}

// Thread class
class Customer extends Thread {

    private BankAccount account;
    private int amt;

    Customer(BankAccount account, int amt, String name) {
        super(name);
        this.account=account;
        this.amt=amt;
    }

    @Override
    public void run() {
        account.withdraw(amt);
    }

}

public class SyncronizedMethodDemo {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Customer c1 = new Customer(account, 700, "Customer-1");
        Customer c2 = new Customer(account, 500, "Customer-2");

        c1.start();
        c2.start();
    } 

}