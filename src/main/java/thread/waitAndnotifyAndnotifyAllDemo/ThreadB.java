package thread.waitAndnotifyAndnotifyAllDemo;

public class ThreadB extends Thread {
    public ThreadB(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " run ");
        // 死循环，不断运行。
        while (true)
            ;
    }
}
