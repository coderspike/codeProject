package thread.ThreadSchool.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}
/*
ExecutorService、Callable、Future对象都是属于Executor框架中的功能类 Executors类，
提供工厂方法用于创建线程池，返回的线程池都实现了ExecutorService接口 创建有返回值的线程 必须实现Callable接口，
无返回值的实现Runable接口

// 创建固定数目线程的线程池
ExecutorService executorService = Executors.newFixedThreadPool(int poolSize);
// 创建一个可缓存的线程池
ExecutorService executorService = Executors.newCachedThreadPool();
// 创建一个单线程化的线程池
ExecutorService executorService = Executors.newSingleThreadExecutor();
// 创建一个支持定时及周期性的任务执行的线程池
ExecutorService executorService = Executors.newScheduledThreadPool(int corePoolSize);

 */