package thread.ThreadSchool.singleton;


import annoations.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建所以是线程安全的
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {
        /*
        饿汉模式的不足，如果这里加载的内容很多的话可能会引起性能问题。
         */
    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
