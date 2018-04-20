package thread.ThreadSchool.count;


import annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.ThreadSchool.sync.SynchronizedExample1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class CountExample4 {
    private static final Logger log = LoggerFactory.getLogger(SynchronizedExample1.class);
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    /*
    volatile 不具备原子性 不适合++场景，适合做全局判断如
    volatile boolean inited =false;
    //线程1
    context = loadContext();
    inited =true;
    //线程2
    while(!intied){
    sleep();
    }
    doSomeThingWihtConfig(context);
     */
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
        // 1、count
        // 2、+1
        // 3、count
    }
}
