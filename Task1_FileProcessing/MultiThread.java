import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileTask extends Thread {

    private String filename;

    public FileTask(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        int count = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while (reader.readLine() != null) {
                count++;
            }

            reader.close();

            System.out.println(filename + ": " + count + " lines");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MultiThread {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String[] files = {
            "file_1.txt",
            "file_2.txt",
            "file_3.txt",
            "file_4.txt",
            "file_5.txt"
        };

        // Create threads
        FileTask[] threads = new FileTask[files.length];

        for (int i = 0; i < files.length; i++) {
            threads[i] = new FileTask(files[i]);
            threads[i].start();  // start thread
        }

        // Wait for all threads to finish
        for (int i = 0; i < files.length; i++) {
            try {
                threads[i].join();  // wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        double timeInSeconds = (endTime - startTime) / 1000.0;

        System.out.println("\nTotal Time (Multi Thread): " 
                            + timeInSeconds + " seconds");
    }
}