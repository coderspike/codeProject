package thread.ThreadSchool.demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) throws Exception {
        BlockingQueue bq = new ArrayBlockingQueue(1000);
        Producer1 producer = new Producer1(bq);
        Consumer1 consumer = new Consumer1(bq);
        new Thread(producer).start();
        new Thread(consumer).start();
        Thread.sleep(4000);
    }
}

/**
 * Producer generate the sum.
 * And add it into the queue
 */
class Producer1 implements Runnable {
    private BlockingQueue bq = null;

    public Producer1(BlockingQueue queue) {
        this.setBlockingQueue(queue);
    }

    // The blocking queue has a internal synchronize
    // The delay of each end of the addition will show this
    public void run() {
        Random rand = new Random();
        int res = 0;
        try {
            res = Addition(rand.nextInt(100), rand.nextInt(50));
            System.out.println("Produced: " + res);
            bq.put(res);
            Thread.sleep(1000);
            res = Addition(rand.nextInt(100), rand.nextInt(50));
            System.out.println("Produced: " + res);
            bq.put(res);
            Thread.sleep(1000);
            res = Addition(rand.nextInt(100), rand.nextInt(50));
            System.out.println("Produced: " + res);
            bq.put(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setBlockingQueue(BlockingQueue bq) {
        this.bq = bq;
    }

    public int Addition(int x, int y) {
        int result = 0;
        result = x + y;
        return result;
    }
}

/**
 * Comsumer take the result from the queue.
 * And print it out to the output
 */
class Consumer1 implements Runnable {
    protected BlockingQueue queue = null;

    public Consumer1(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Consumed: " + queue.take());
            System.out.println("Consumed: " + queue.take());
            System.out.println("Consumed: " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}