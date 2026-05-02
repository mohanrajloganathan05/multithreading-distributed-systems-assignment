class BankAccount {
    int balance = 10000000;

    public synchronized void withdraw(int amount) {
        balance = balance - amount;  // Thread-safe ✅
    }

    public int getBalance() {
        return balance;
    }
}

class Worker extends Thread {
    BankAccount account;

    public Worker(BankAccount account) {
        this.account = account;
    }

    public void run() {
        account.withdraw(1000);
    }
}

public class WithSync {
    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccount();
        Thread[] workers = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            workers[i] = new Worker(account);
            workers[i].start();
        }

        for (int i = 0; i < 1000; i++) {
            workers[i].join();
        }

        System.out.println("Final Balance (With Sync): " + account.getBalance());
    }
}