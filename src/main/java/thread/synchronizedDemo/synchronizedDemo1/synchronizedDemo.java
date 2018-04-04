package thread.synchronizedDemo.synchronizedDemo1;

public class synchronizedDemo {
    /*
    在java中，每一个对象有且仅有一个同步锁。这也意味着，同步锁是依赖于对象而存在。
    当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。例如，synchronized(obj)就获取了“obj这个对象”的同步锁。[原来如此]
    对象的同步锁只能被一个线程获取到
     */

    //synchronized方法示例
    public synchronized void foo1() {
        System.out.println("synchronized methoed");
    }

    //synchronized代码块
    public void foo2() {
        synchronized (this) {
            System.out.println("synchronized methoed");
        }
    }
}
/*
实例锁 和 全局锁 还没看
 */