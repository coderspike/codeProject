package thread.ThreadSchool.syncContainer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingQueueTest {
    /*
    示例本身是想展示从阻塞队列里有10个元素，初始化带有2个线程的线程池，跑2个线程分别去阻塞队列里取数据执行
    事实上并没有跑2个，没太明白。
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build());
        final LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<String>();
        for (int i = 1; i <= 10; i++) {
            deque.add(i + "");
        }
        es.submit(new Runnable() {
            @Override
            public void run() {
                while (!deque.isEmpty()) {
                    System.out.println(deque.poll() + "-" + Thread.currentThread().getName());
                }
            }
        });
        es.submit(new Runnable() {
            @Override
            public void run() {
                while (!deque.isEmpty()) {
                    System.out.println(deque.poll() + "-" + Thread.currentThread().getName());
                }
            }
        });
        Thread.sleep(10000l);
    }
}
