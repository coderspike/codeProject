package thread.ThreadSchool.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerPattern {
    public static void main(String[] args) {
        BlockingQueue sharedQueue = new LinkedBlockingDeque<>();
        Thread pThread = new Thread(new Producer(sharedQueue));
        Thread cThread = new Thread(new Consumer(sharedQueue));
        pThread.start();
        cThread.start();
    }
}

class Producer implements Runnable {
    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("Producer Put  Value : " + i);
                sharedQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue sharedQueue;

    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed Take Value : " + sharedQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}