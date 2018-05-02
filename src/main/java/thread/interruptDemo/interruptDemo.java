package thread.interruptDemo;

public class interruptDemo {
    /*
    interrupt()的作用是中断本线程。
     */

    /*
    1 终止处于阻塞状态的线程
    当线程由于被调用了sleep(), wait(), join()等方法而进入阻塞状态；
    若此时调用线程的interrupt()将线程的中断标记设为true。
    由于处于阻塞状态，中断标记会被清除，同时产生一个InterruptedException异常。
    将InterruptedException放在适当的为止就能终止线程
    @Override
    public void run() {
        try {
            while (true) {
                // 执行任务...
            }
        } catch (InterruptedException ie) {
            // 由于产生InterruptedException异常，退出while(true)循环，线程终止！
        }
    }
    2 终止处于“运行状态”的线程
    (01) 通过“中断标记”终止线程。
    @Override
    public void run() {
    while (!isInterrupted()) {
        // 执行任务...
    }
    }
    isInterrupted()是判断线程的中断标记是不是为true。当线程处于运行状态，并且我们需要终止它时；
    可以调用线程的interrupt()方法，使用线程的中断标记为true，即isInterrupted()会返回true。此时，就会退出while循环。
    (02) 通过“额外添加标记”。
    private volatile boolean flag= true;
    protected void stopTask() {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            // 执行任务...
        }
    }
     */

    /*
    interrupted() 和 isInterrupted()的区别
    最后谈谈 interrupted() 和 isInterrupted()。
    interrupted() 和 isInterrupted()都能够用于检测对象的“中断标记”。
    区别是，interrupted()除了返回中断标记之外，它还会清除中断标记(即将中断标记设为false)；而isInterrupted()仅仅返回中断标记。
     */
}
