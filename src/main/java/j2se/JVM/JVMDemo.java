package j2se.JVM;

public class JVMDemo {
    /*
    JVM
        ---类加载器 类的加载指的是将类的.class文件中的二进制数据读入到内存中
        ---执行引擎
        ---运行时数据区

    运行时数据区：
        --共享 --方法区 类信息
               --堆区 对象(由于现在的垃圾收集器都采用分代收集算法，所以堆空间还可以细分为新生代和老生代)
               [类的加载的最终产品是位于堆区中的Class对象，Class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区内的数据结构的接口。]

        --非共享 --虚拟机栈 局部变量
                 --本地方法栈 native
                 --程序计数器

    类的加载过程:
    加载-验证-准备-解析-初始化

    内存分区:
    线程共享数据区：
    方法区: 存储已被虚拟机加载的类信息、常量、静态变量、即时编译后代码等数据。
    堆区: 用于存放对象实例和数组
    线程私有数据区：
    虚拟机栈: 方法执行时创建一个栈帧，用于存储局部变量、操作数栈、动态链接、方法出口等消息
    本地方法栈: 用于存放执行Native方法运行数据
    程序计数器: 当前线程所执行字节码指示器，改变计数器指向选取下一条字节码指令

    JVM：
    (1)类加载器，用来加载.class文件
    (2)执行引擎，用来执行字节码文件或本地方法
    (3)运行时数据区,包括堆、栈、方法区、程序计数器、本地方法栈

    GC：
    在JVM中，有一个垃圾回收线程，它是低优先级的，在正常情况下是不会执行的，只有在虚拟机空闲或者当前堆内存不足时，才会触发执行，
    扫面那些没有被任何引用的对象，并将它们添加到要回收的集合中，进行回收。
    我们常用的垃圾回收器一般都采用分代收集算法。把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。
    在释放对象占用的内存之前，垃圾收集器会调用对象的finalize()方法。一般建议在该方法中释放对象持有的资源。

    分代收集算法，“分代收集”（Generational Collection）算法，把Java堆分为新生代和老年代，
    这样就可以根据各个年代的特点采用最适当的收集算法。

    如何判断对象可以被回收？
    判断对象是否存活一般有两种方式：
    引用计数：每个对象有一个引用计数属性，新增一个引用时计数加1，引用释放时计数减1，计数为0时可以回收。此方法简单，无法解决对象相互循环引用的问题。
    可达性分析（Reachability Analysis）：从GC Roots开始向下搜索，搜索所走过的路径称为引用链。当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的，不可达对象。
     */
}
