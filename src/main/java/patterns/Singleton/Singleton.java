package patterns.Singleton;

/**
 * 线程不安全懒汉式
 * 线程不安全：主要在于instance = new Singleton()这句，这并非是一个原子操作
 */
public class Singleton {
    private static Singleton instance;

    //私有构造器
    //对象的创建就是通过构造方法来完成，其功能主要是完成对象的初始化。
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

/**
 * 线程安全懒汉式
 * 其实就是给方法加上了synchronized，线程安全但效率低。
 */
class Singleton2 {
    private static Singleton2 instance2;

    private Singleton2() {
    }

    //new操作不是原子操作，所以加在这里来控制线程安全
    public static synchronized Singleton2 getInstance() {
        if (instance2 == null) {
            instance2 = new Singleton2();
        }
        return instance2;
    }
}

/**
 * 饿汉式
 */
class Singleton3 {
    //类加载时就初始化
    private static final Singleton3 instance3 = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance3;
    }
}

/**
 * 静态内部类
 * 这种写法仍然使用JVM本身机制保证了线程安全问题
 */
class Singleton4 {
    private static class SingletonHolder4 {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4() {
    }

    public static final Singleton4 getInstance() {
        return SingletonHolder4.INSTANCE;
    }
}

/**
 * 枚举创建单例
 * 通过EasySingleton.INSTANCE来访问实例
 * 线程安全
 */
enum EasySingleton {
    INSTANCE;

    public String say() {
        return "枚举创建单例";
    }
}