package thread.ThreadSchool.syncContainer.blockingQueueTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingMain {
    final int CPU_COUNT = 4;
    // ��������
    BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();
    // �̳߳�
    ThreadPoolExecutor executor = new ThreadPoolExecutor(CPU_COUNT,
            CPU_COUNT, 60L, TimeUnit.SECONDS, taskQueue);
    /**
     * ����
     *
     * @param task
     */
    public void runOrQueueTask(SampleTask task) {
        executor.execute(task);
    }

    /**
     * �ر�
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
