package thread.ThreadSchool.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Context {
    private ReentrantLock lock = new ReentrantLock();

    public void method() {
        lock.lock();
        System.out.println("do atomic operation");
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyThread implements Runnable {
    private Context context;

    public MyThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        context.method();
    }
}

class Test {
    public static void main(String[] args) {
        Context context = new Context();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyThread(context));
        }
    }
}
/*
输出结果，每隔3秒输出：
do atomic operation
如果没有使用可重入锁的话，那么一次性输出5条 do atomic operation。
 */