package thread.interruptDemo;

public class interruptDemo {
    /*
    interrupt()的作用是中断本线程。
     */

    /*
    终止处于阻塞状态的线程
    当线程由于被调用了sleep(), wait(), join()等方法而进入阻塞状态；若此时调用线程的interrupt()将线程的中断标记设为true。
    由于处于阻塞状态，中断标记会被清除，同时产生一个InterruptedException异常。将InterruptedException放在适当的为止就能终止线程
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

     */
}
