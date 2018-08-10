package thread.ThreadPoolExecutorDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    /*
    线程池为线程生命周期开销问题和资源不足问题提供了解决方案。
    我们可以通过线程池做到线程复用，不需要频繁的创建和销毁线程，让线程池中的线程一直存在于线程池中，
    然后线程从任务队列中取得任务来执行。
    java.util.concurrent.ThreadPoolExecutor类是线程池中最核心的一个类

    Executor：所有线程池的接口，只有一个方法
    Executors：Executor 的工厂类，提供了创建各种不同线程池的方法，返回的线程池都实现了ExecutorService 接口
    ThreadPoolExecutor：线程池的具体实现类，一般所有的线程池都是基于这个类实现的

    Java API针对不同需求，利用Executors类提供了4种不同的线程池：
    newCachedThreadPool, newFixedThreadPool, newScheduledThreadPool, newSingleThreadExecutor

    ThreadPoolExecutor的构造方法
    corePoolSize：线程池的核心线程数，线程池中运行的线程数也永远不会超过 corePoolSize 个，
    默认情况下会永远存活
    maximumPoolSize：线程池中允许的最大线程数
    keepAliveTime：空闲线程结束的超时时间
    unit：是一个枚举，它表示的是 keepAliveTime 的单位
    workQueue：工作队列，用于任务的存放
    public ThreadPoolExecutor(int corePoolSize,
						  int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue) {

	this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
	Executors.defaultThreadFactory(), defaultHandler);

}
     */

    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5, 10, 200, TimeUnit.MICROSECONDS,
                        new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中的线程数目： " + executor.getPoolSize()
                    + "， 队列中等待执行的任务数目： " + executor.getQueue().size()
                    + "，已执行完成的任务数目： " + executor.getCompletedTaskCount());
            ;
        }
        executor.shutdown();

    }
}
/*
LinkedBlockingQueue一般用于任务相互之间独立，没有交叉，可独立执行。
 */
