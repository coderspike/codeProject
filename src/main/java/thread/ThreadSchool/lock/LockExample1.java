package thread.ThreadSchool.lock;

import annoations.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class LockExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*
        信号量Semaphore
        信号量维护一个许可集，可通过acquire()获取许可（若无可用许可则阻塞），通过release()释放许可，
        从而可能唤醒一个阻塞等待许可的线程。

        与互斥锁类似，信号量限制了同一时间访问临界资源的线程的个数，并且信号量也分公平信号量与非公平信号量。
        而不同的是，互斥锁保证同一时间只会有一个线程访问临界资源，而信号量可以允许同一时间多个线程访问特定资源。
        所以信号量并不能保证原子性。

        信号量的一个典型使用场景是限制系统访问量。每个请求进来后，处理之前都通过acquire获取许可，若获取许可成功则处理该请求，
        若获取失败则等待处理或者直接不处理该请求。
         */
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

    private synchronized static void add() {
        count++;
    }
}
