package j2se.aboutClass;

public class objectClass implements Cloneable {
    /**
     * object 包含的方法
     * <p> 包含了如下11个方法
     * public final native Class<?> getClass()
     * public native int hashCode()
     * public boolean equals(Object obj)
     * protected native Object clone() throws CloneNotSupportedException
     * public String toString()
     * public final native void notify()
     * public final native void notifyAll()
     * public final native void wait(long timeout) throws InterruptedException
     * public final void wait(long timeout, int nanos) throws InterruptedException
     * public final void wait() throws InterruptedException
     * protected void finalize() throws Throwable { }
     */
    public static void objectClass() {
        // TODO: 2018-03-19  1、getClass方法是一个final方法，不允许子类重写，并且也是一个native方法
        Father father = new Father();
        System.out.println(father.getClass());
        // TODO: 2018-03-19 2、hashCode方法也是一个native方法。该方法返回对象的哈希码，主要使用在哈希表中，比如JDK中的HashMap。 
        System.out.println(father.hashCode());
        // TODO: 2018-03-19 3、创建并返回当前对象的一份拷贝,Object类的clone方法是一个protected的native方法。由于Object本身没有实现Cloneable接口，所以不重写clone方法并且进行调用的话会发生CloneNotSupportedException异常。
        // TODO: 2018-03-19 4、toString方法Object对象的默认实现，即输出类的名字@实例的哈希码的16进制。
        // TODO: 2018-03-19 5、notify方法final native。
        // TODO: 2018-03-19 6、跟notify一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程
        // TODO: 2018-03-19 7、wait方法 wait方法会让当前线程(我们先叫做线程T)将其自身放置在对象的等待集中
        // TODO: 2018-03-19 8、finalize方法该方法的作用是实例被垃圾回收器回收的时候触发的操作 
    }

    @Override
    public String toString() {
        return "objectClass{}";
    }

}
