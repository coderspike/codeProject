package thread.ThreadSchool.atomic;

public class atomicNote {
    /*
    基础类型变量自增（i++）是一种常被新手误以为是原子操作而实际不是的操作。
    Java中提供了对应的原子操作类来实现该操作，并保证原子性，其本质是利用了CPU级别的CAS指令。
    由于是CPU级别的指令，其开销比需要操作系统参与的锁的开销小。AtomicInteger使用方法如下。
    AtomicInteger atomicInteger = new AtomicInteger();
    for(int b = 0; b < numThreads; b++) {
      new Thread(() -> {
        for(int a = 0; a < iteration; a++) {
          atomicInteger.incrementAndGet();
        }
      }).start();
    }



     */
}
