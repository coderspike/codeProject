package thread.ThreadSchool.count;


import annoations.annoations.NotThreadSafe;
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

    Java如何保证可见性
    Java提供了volatile关键字来保证可见性。当使用volatile修饰某个变量时，它会保证对该变量的修改会立即被更新到内存中，
    并且将其它缓存中对该变量的缓存设置成无效，因此其它线程需要读取该值时必须从主内存中读取，从而得到最新的值。

    Java中可通过volatile在一定程序上保证顺序性，另外还可以通过synchronized和锁来保证顺序性。

    volatile变量规则：对一个被volatile修饰的写操作先发生于后面对该变量的读操作
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
