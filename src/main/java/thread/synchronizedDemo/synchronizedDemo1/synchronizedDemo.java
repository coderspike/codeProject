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
synchronized
– 指定加锁对象：对给定对象加锁，进入同步代码前要获得给定对象的锁。
– 直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
– 直接作用于静态方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁。
 */