package thread.ThreadSchool.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        barrier.await();
        log.info("{} continue", threadNum);
    }
}

/*
13:34:43.018 [pool-1-thread-1] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 0 is ready
13:34:44.021 [pool-1-thread-2] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 1 is ready
13:34:45.035 [pool-1-thread-3] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 2 is ready
13:34:46.037 [pool-1-thread-4] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 3 is ready
13:34:47.038 [pool-1-thread-5] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 4 is ready
13:34:47.038 [pool-1-thread-5] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 4 continue
13:34:47.038 [pool-1-thread-1] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 0 continue
13:34:47.038 [pool-1-thread-2] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 1 continue
13:34:47.038 [pool-1-thread-3] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 2 continue
13:34:47.038 [pool-1-thread-4] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 3 continue
13:34:48.052 [pool-1-thread-6] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 5 is ready
13:34:49.066 [pool-1-thread-1] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 6 is ready
13:34:50.080 [pool-1-thread-2] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 7 is ready
13:34:51.094 [pool-1-thread-3] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 8 is ready
13:34:52.108 [pool-1-thread-4] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 9 is ready
13:34:52.108 [pool-1-thread-4] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 9 continue
13:34:52.108 [pool-1-thread-6] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 5 continue
13:34:52.108 [pool-1-thread-1] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 6 continue
13:34:52.108 [pool-1-thread-2] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 7 continue
13:34:52.108 [pool-1-thread-3] INFO thread.ThreadSchool.aqs.CyclicBarrierExample1 - 8 continue
 */