package thread.ThreadSchool.syncContainer;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        final LinkedBlockingQueue<String> deque = new LinkedBlockingQueue<String>();
        for (int i = 1; i <= 10; i++) {
            deque.add(i + "");
        }
        for (Iterator<String> iterator = deque.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(next);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(10000l);
    }
}
