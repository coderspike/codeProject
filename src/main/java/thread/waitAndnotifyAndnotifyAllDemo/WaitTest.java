package thread.waitAndnotifyAndnotifyAllDemo;

public class WaitTest {

    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");

        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName() + " wait()");
                t1.wait();

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
main start t1
main wait()
t1 call notify()
main continue


等待在当前对象上的 wait队列
notify 随机唤醒一个。
 */