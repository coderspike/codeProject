package thread.ThreadSchool.singleton;


import annoations.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
             /*
              多线程情况下运行到19-21行时，多个线程同时判断为null,new了多个对象，不再是线程安全。
             */
            instance = new SingletonExample1();
        }
        return instance;
    }
}
