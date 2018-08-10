package thread.synchronizedDemo.synchronizedDemo1;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        synchronized (this) { //this看作是“demo这个Runnable对象”,线程t1和t2共享“demo对象的同步锁”
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}
/*
t1 loop 0
t1 loop 1
t1 loop 2
t1 loop 3
t1 loop 4
t2 loop 0
t2 loop 1
t2 loop 2
t2 loop 3
t2 loop 4

synchronized几种用法
每个Java对象都可以用做一个实现同步的互斥锁，这些锁被称为内置锁。线程进入同步代码块或方法时自动获得内置锁，退出同步代码块或方法时自动释放该内置锁。进入同步代码块或者同步方法是获得内置锁的唯一途径。

实例同步方法
synchronized用于修饰实例方法（非静态方法）时，执行该方法需要获得的是该类实例对象的内置锁（同一个类的不同实例拥有不同的内置锁）。如果多个实例方法都被synchronized修饰，则当多个线程调用同一实例的不同同步方法（或者同一方法）时，需要竞争锁。但当调用的是不同实例的方法时，并不需要竞争锁。

静态同步方法
synchronized用于修饰静态方法时，执行该方法需要获得的是该类的class对象的内置锁（一个类只有唯一一个class对象）。调用同一个类的不同静态同步方法时会产生锁竞争。

同步代码块
synchronized用于修饰代码块时，进入同步代码块需要获得synchronized关键字后面括号内的对象（可以是实例对象也可以是class对象）的内置锁。
 */
