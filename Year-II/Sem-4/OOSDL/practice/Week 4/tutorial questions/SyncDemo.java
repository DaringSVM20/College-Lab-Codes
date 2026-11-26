class BankAccount {

    private int bal = 1000;

    synchronized void withdraw(int amt) {

        System.out.println(Thread.currentThread().getName() + " is trying to withdraw " + amt);
        
        if (bal >= amt) {
            System.out.println(Thread.currentThread().getName() + " is processing withdrawal... ");
            
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            bal -= amt;
            System.out.println(Thread.currentThread().getName() + " has withdrawn. Remaining balance: " + bal);
            
        } else {
            
            System.out.println(Thread.currentThread().getName() + " has insufficient balance. Withdrawal failed.");

        }

    }

}

class Customer extends Thread {

    private BankAccount account;
    private int amt;

    Customer(BankAccount account, int amt, String name) {
        super(name);
        this.account = account;
        this.amt = amt;
    }

    @Override
    public void run() {
        account.withdraw(amt);
    }

}

public class SyncDemo {
    public static void main(String[] args) {
        
        BankAccount acc = new BankAccount();

        Customer c1 = new Customer(acc, 700, "Customer 1");
        Customer c2 = new Customer(acc, 500, "Customer 2");

        c1.start();
        c2.start();

    }
}