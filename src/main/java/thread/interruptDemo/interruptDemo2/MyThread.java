package thread.interruptDemo.interruptDemo2;

public class MyThread extends Thread {
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }
}

// 实际上只是打了标记但不暂停
class Run {
    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
中断线程：
Thread thread = new Thread(() -> {
    while (!Thread.interrupted()) {
        // do more work.
    }
});
thread.start();

// 一段时间以后
thread.interrupt();
 */