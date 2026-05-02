import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Parking {
    int count = 0; // shared variable
}

class Car implements Runnable {
    Parking parking;

    public Car(Parking parking) {
        this.parking = parking;
    }

    public void run() {
        parking.count++; // NOT thread-safe ❌
        System.out.println(Thread.currentThread().getName() + " parked. Count: " + parking.count);
    }
}

public class NoSyncNoSemaphore {
    public static void main(String[] args) {

        System.out.println("No Sync No Semaphore:");
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