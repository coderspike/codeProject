package thread.ThreadSchool.aqs;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/*
AbstractQueuedSynchronizer，即队列同步器(aqs)
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThread = 3;
        long start = System.currentTimeMillis();
        CountDownLatch countDown = new CountDownLatch(totalThread);
        for (int i = 0; i < totalThread; i++) {
            final String threadName = "Thread " + i;
            new Thread(() -> {
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                countDown.countDown();
                System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
            }).start();
            ;
        }
        countDown.await();
        long stop = System.currentTimeMillis();
        System.out.println(String.format("Total time : %sms", (stop - start)));
    }
}

/*
Sun Jun 19 20:34:31 CST 2016  Thread 1 started
Sun Jun 19 20:34:31 CST 2016  Thread 0 started
Sun Jun 19 20:34:31 CST 2016  Thread 2 started
Sun Jun 19 20:34:32 CST 2016  Thread 2 ended
Sun Jun 19 20:34:32 CST 2016  Thread 1 ended
Sun Jun 19 20:34:32 CST 2016  Thread 0 ended
Total time : 1072ms
主线程等待所有3个线程都执行结束后才开始执行。

CountDownLatch主要接口分析
CountDownLatch工作原理相对简单，可以简单看成一个倒计数器，在构造方法中指定初始值，
每次调用countDown()方法时将计数器减1，而await()会等待计数器变为0。CountDownLatch关键接口如下

countDown() 如果当前计数器的值大于1，则将其减1；若当前值为1，则将其置为0并唤醒所有通过await等待的线程；
若当前值为0，则什么也不做直接返回。
await() 等待计数器的值为0，若计数器的值为0则该方法返回；若等待期间该线程被中断，
则抛出InterruptedException并清除该线程的中断状态。
await(long timeout, TimeUnit unit) 在指定的时间内等待计数器的值为0，若在指定时间内计数器的值变为0，则该方法返回true；
若指定时间内计数器的值仍未变为0，则返回false；若指定时间内计数器的值变为0之前当前线程被中断，
则抛出InterruptedException并清除该线程的中断状态。
getCount() 读取当前计数器的值，一般用于调试或者测试。
 */