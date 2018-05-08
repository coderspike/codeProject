package thread.ThreadPoolExecutorDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
    创建一个只有线程的线程池，该方法无参数，所有任务都保存队列LinkedBlockingQueue中，
    等待唯一的单线程来执行任务，并保证所有任务按照指定顺序(FIFO或优先级)执行。
 */
public class singleThreadExecutorDemo {
    public void singleThreadExecutorDemo() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            final int index = i;

            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", index=" + index);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
