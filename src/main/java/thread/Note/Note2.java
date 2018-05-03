package thread.Note;

public class Note2 {
    /*
    什么是线程
    线程的基本操作
    守护线程
    线程优先级
    基本的线程同步操作
     */


    /*
    停止线程：
    interrupt()方法的使用效果并不像for+break语句那样，马上就停止循环。
    调用interrupt方法是在当前线程中打了一个停止标志，并不是真的停止线程。
    比如下面的实例，实际上是不会让线程终止的。
    public class MyThread extends Thread {
        public void run(){
            super.run();
            for(int i=0; i<500000; i++){
                System.out.println("i="+(i+1));
            }
        }
    }

    public class Run {
        public static void main(String args[]){
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

    为了判断线程是否属于终止状态
    Thread.java类中提供了两种方法：
    this.interrupted(): 测试当前线程是否已经中断，并清除中断状态（即上面提到的标识）；
    this.isInterrupted(): 测试当前线程是否已经中断；


    Java内存模型：
    原子性
    原子性是指一个操作是不可中断的。即使是在多个线程一起执行的时候，一个操作一旦开始，就
    不会被其它线程干扰。
    i++ 并不是一个原子操作

    有序性

    可见性

    Happen-Before
     程序顺序原则：一个线程内保证语义的串行性
     volatile规则：volatile变量的写，先发生于读，这保证了volatile变量的可见性
     锁规则：解锁（unlock）必然发生在随后的加锁（lock）前
     传递性：A先于B，B先于C，那么A必然先于C
     线程的start()方法先于它的每一个动作
     线程的所有操作先于线程的终结（Thread.join()）
     线程的中断（interrupt()）先于被中断线程的代码
     对象的构造函数执行结束先于finalize()方法

    线程安全的概念

     */


    /*
    stop 已经不推荐使用 它会释放所有monitor 太暴力，可能导致数据异常

    普通的中断方法：
    public void run(){
    while(true){
    Thread.yield();
    }
    }
    t1.interrupt();

    一般是这样的处理
    public void run(){
    while(true){
    if(Thread.currentThread().isInterrupted()){
    System.out.println("Interruted!");
    break;
    }
    Thread.yield();
    }
    }

    这样处理：
    public void run(){
    while(true){
    if(Thread.currentThread().isInterrupted()){
    System.out.println("Interruted!");
    break;
    }
    try {
    Thread.sleep(2000);
    } catch (InterruptedException e) {
    System.out.println("Interruted When Sleep");
    //设置中断状态，抛出异常后会清除中断标记位
    Thread.currentThread().interrupt();
    }
    Thread.yield();
    }
    }


     */
}
