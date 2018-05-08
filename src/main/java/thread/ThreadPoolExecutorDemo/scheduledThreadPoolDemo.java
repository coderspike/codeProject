package thread.ThreadPoolExecutorDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scheduledThreadPoolDemo {
    public void scheduledThreadPoolDemo() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //定时执行一次的任务，延迟1s后执行
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", delay 1s");
            }
        }, 1, TimeUnit.SECONDS);

        //周期性地执行任务，延迟2s后，每3s一次地周期性执行任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", every 3s");
            }
        }, 2, 3, TimeUnit.SECONDS);
    }
}
