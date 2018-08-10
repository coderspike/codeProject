package thread.ThreadSchool.syncContainer.blockingQueueTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingMain {
    final int CPU_COUNT = 4;
    // 阻塞队列
    BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();
    // 线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(CPU_COUNT,
            CPU_COUNT, 60L, TimeUnit.SECONDS, taskQueue);
    /**
     * 运行
     *
     * @param task
     */
    public void runOrQueueTask(SampleTask task) {
        executor.execute(task);
    }

    /**
     * 关闭
     */
    public void shutDownNow() {
        executor.shutdownNow();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        BlockingMain main = new BlockingMain();
        for (int i=1;i<=30;i++) {
            main.runOrQueueTask(new SampleTask(String.valueOf(i)));
        }
        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.shutDownNow();
    }

}
