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
