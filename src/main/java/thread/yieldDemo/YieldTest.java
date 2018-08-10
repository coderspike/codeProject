package thread.yieldDemo;

public class YieldTest {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }
}
/*
“线程t1”在能被4整数的时候，并没有切换到“线程t2”。这表明，yield()虽然可以让线程由“运行状态”进入到“就绪状态”；
但是，它不一定会让其它线程获取CPU执行权(即，其它线程进入到“运行状态”)，即使这个“其它线程”与当前调用yield()的线程具有相同的优先级。
t1 [5]:0
t2 [5]:0
t1 [5]:1
t1 [5]:2
t1 [5]:3
t1 [5]:4
t1 [5]:5
t2 [5]:1
t2 [5]:2
t2 [5]:3
t2 [5]:4
t2 [5]:5
t2 [5]:6
t2 [5]:7
t2 [5]:8
t1 [5]:6
t2 [5]:9
t1 [5]:7
t1 [5]:8
t1 [5]:9

讲下join,yield方法的作用,以及什么场合用它们？

join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行。

yield方法可以暂停当前正在执行的线程对象，让其它有相同优先级的线程执行。
它是一个静态方法而且只保证当前线程放弃CPU占用而不能保证使其它线程一定能占用CPU，执行yield()的线程有可能在进入到暂停状态后马上又被执行。
 */
