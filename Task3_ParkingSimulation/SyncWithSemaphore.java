import java.util.concurrent.*;

class Parking {
    int count = 0;
    Semaphore semaphore = new Semaphore(3);

    public synchronized void park() {
        count++;
    }
}

class Car implements Runnable {
    Parking parking;

    public Car(Parking parking) {
        this.parking = parking;
    }

    public void run() {
        try {
            parking.semaphore.acquire();

            parking.park(); // thread-safe 
            System.out.println(Thread.currentThread().getName() + " parked. Count: " + parking.count);

            Thread.sleep(500); // simulate parking time

            parking.semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SyncWithSemaphore {
    public static void main(String[] args) {
        System.out.println(" Sync with Semaphore:");


        Parking parking = new Parking();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Car(parking));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        System.out.println("Final Count: " + parking.count);
    }
}