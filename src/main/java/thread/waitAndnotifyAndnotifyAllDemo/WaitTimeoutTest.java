package thread.waitAndnotifyAndnotifyAllDemo;

public class WaitTimeoutTest {

    public static void main(String[] args) {

        ThreadB t1 = new ThreadB("t1");

        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " call wait ");
                t1.wait(3000);

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
main start t1
main call wait
t1 run                  // 大约3秒之后...输出“main continue”
main continue

wait(long timeout)会让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
下面的示例就是演示wait(long timeout)在超时情况下，线程被唤醒的情况。
 */