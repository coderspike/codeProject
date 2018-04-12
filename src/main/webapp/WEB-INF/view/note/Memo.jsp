<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-04-12
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Memo</title>
</head>
<body>
<%--自我介绍--%>


<%--j2se--%>

如何判断对象可以被回收？
一个是引用计数法，新增一个引用计数加1，减少一个引用计数减1，当为0时候进行回收。另外一种格是可达性分析法，通过引用链向下搜索，没有引用连接时，就回收。

jvm的结构是什么样的？
jvm分为三部分，运行时数据区，类加载器，和执行引擎（总是忘啊！）。

jvm的内存结构是什么样的？
运行时数据区分为线程共享内存和不共享内存两种，共享内存有方法区和堆，不共享的区域有虚拟机栈，本地方法栈，和程序计数器。

堆和栈有什么区别？
堆中主要存放对象，线程共享，分代垃圾手机算法主要也是针对堆来说的。栈，线程不共享，主要存储局部变量等信息。

类加载的过程是什么样的？
加载-验证-准备-解析-初始化
加载是将类文件读入到数据区，形成class对象的过程。
验证主要是做一些格式验证看是否符合规范
准备是内存分配

类的加载器有几种？
主要有启动类加载器
扩展类加载器
应用程序类加载器


面向对象的特点？
封装继承多态，封装继承从某种个意义讲都是为了多态服务的，封装就是隐藏一切可以隐藏的信息，实体类就是一种封装，继承是指
子类继承父类的一些属性和方法。多态有两种情况编译时的多态和运行时的多态，类的重载（overload）是自己类的方法中参数的不同，这也是
一种多态，我们一般指的多态是运行时的多态，即一、要有继承； 二、要有重写（override）； 三、父类引用指向子类对象。多态使程序灵活性比较好


== 和 equals区别
基础类型 ==比较值
当为类的时候==比较的是内存的地址。
JAVA当中所有的类都是继承于Object这个基类的，在Object中的基类中定义了一个equals的方法，这个方法的初始行为是比较对象的内存地址，但在一些类库当中这个方法被覆盖掉了，如String,Integer,Date在这些类当中equals有其自身的实现，而不再是比较类在堆内存中的存放地址了。


创建对象有几种方式呀？
第一种当然是通过new的方式啦
第二种是通过反射new Instance的方式
第三种可以通过反序列化的方式

hashCode和equals方法的关系
equals相等，hashcode必相等；hashcode相等，equals可能不相等。
重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。

object 有哪些方？
toString
equals
hashCode
wait
notify
notifyAll
getClass
clone
finalize

写一个单利模式？
public class Singleton{
private Static Singleton instance;

private Singleton(){
}

//线程不安全，因为new不是一个原子性操作
public static [synchronized] Singleton getInstance(){
if(instance==null){
instance = new Singleton();
}
return instance;
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


简单工厂模式：根据参数的不同，返回不同的类型
public class PayFactory {
public static Pay choicePay(String payMethod) {
Pay pay = null;
if ("aliPay".equals(payMethod)) {
pay = new AliPay();
} else if ("wechatPay".equals(payMethod)) {
pay = new WechatPay();
} else if ("unionPay".equals(payMethod)) {
pay = new UnionPay();
}
return pay;
}


什么是序列化？
序列化就是实体和字符序列之间的转换。


说一说常用的集合呗？
开发中常用的集合主要分为两类即collection和Map
collection接口下主要有list和set两个分类，

list接口的主要特点是有序，可以重复等。主要实现类有
arrayList和LinkList两种，arrayList实际上是一种动态数组，可以重复，有顺序，取值比较快。linkList实际是双向链表，内部类entry保有next和previous属性
可以重复，无限量。删除插入值快，遍历较慢。
动态扩容是这样实现的，判断是否超过最大容量，如果超过了调用方法生成1.5倍原来大小的数组，将旧数组拷贝进去。

set接口下的实现主要有，hashSet,LinkHashSet,TreeSet,set 无序，不可重复，所以可以用来进行去重操作。
实际上hashSet的底层就是hashMap,只不过对HashMap中的内部类entry进行了修改，只有key Value是一个默认值，同样采用的hash算法进行分配

另外一种是Map,这是一种keyValue的结构，HashMap底层实际上是数组加链表的实现方式，
(每个节点都是一个node对象，
final int hash; //用来定位数组索引位置
final K key;
V value;
Node<> next;)
通过hash算法确定位置，如果发生了hash碰撞则采用单链法解决
因为初始值有限，hashMap也能自动扩容。
默认情况下，数组大小为16，那么当hashmap中元素个数超过160.75=12的时候，就把数组的大小扩展为2*16=32。
线程不安全
再Java 5以后，有了一个线程安全的HashMap——ConcurrentHashMap，ConcurrentHashMap相对于HashTable来说，
ConcurrentHashMap将hash表分为16个桶（默认值），诸如get,put,remove等常用操作只锁当前需要用到的桶。
试想，原来只能一个线程进入，现在却能同时16个写线程进入（写线程才需要锁定，而读线程几乎不受限制，并发性的提升是显而易见。

如果需要有保有插入顺序可以使用linkedHashMap,与hashMap最大的不同就是在内部类上加了before和after属性，形成了一个双向链表。

TreeMap底层是红黑树，插入顺序按key排序。

线程安全方面，以上两种都不是线程安全的，可以采用Collections.synchronizedList(List list)返回线程安全的list

常用的比较方法是实现Comparator和Comparable接口，Comparator相对灵活一些不用改变类。


<%--数据库--%>
数据库分库分表策略
对于海量数据的数据库，如果是因为表多而数据多，这时候适合使用垂直切分，即把关系紧密（比如同一模块）的表切分出来放在一个server上。
如果表并不多，但每张表的数据非常多，这时候适合水平切分，即把表的数据按某种规则（比如按ID散列）切分到多个数据库(server)上。


<%--项目介绍--%>

<%--io--%>

<%--多线程--%>
多看看把。。。。

多线程有什么用？
提高效率，重复操作的场景可以用多线程处理。

什么是线程安全？
就是当多线程进行访问的时候，采用加锁机制，当一个线程访问这个类的某个数据时，
进行保护， 其他线程不能进行访问直到该线程读取完毕，其他线程才可以使用。不会出现数据不一致或者数据污染。

了解多线程么？
实现多线程常用的两种方法分别是实现runnable接口和继承Thread类。因为java是单继承，所以实现Runnable接口的线程更灵活一些。
区别是实现Runnable的接口的线程可以共享操作数据。

线程有几种状态，即新建，就绪，运行，阻塞，死亡等

start()方法和Run()方法有什么区别？
start()方法会启动一个新的线程而run方法只会在原来线程中启动

wait和sleep()方法的区别？
wait方法释放锁，是object中的方法，一般与notify等共用，用于线程之间的交互。
sleep()方法来自Thread类，一般用于暂停执行，暂停期间依然保持对象锁。暂停过后恢复到就绪状态。
sleep()时间到了就变成就绪状态了必须传参。

线程池：在程序启动的时候就创建若干线程来响应处理。

线程的sleep()方法和yield()方法有什么区别？
线程执行sleep()方法后转入阻塞（blocked）状态，而执行yield()方法后转入就绪（ready）状态；

ThreadLocal和同步机制?
同步机制是为了同步多个线程对相同资源的并发访问，是为了多个线程之间进行通信的有效方式；
而ThreadLocal是隔离多个线程的数据共享，从根本上就不在多个线程之间共享资源（变量），这样当然不需要对多个线程进行同步了。

讲下join,yield方法的作用,以及什么场合用它们？
join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行。
yield方法可以暂停当前正在执行的线程对象，让其它有相同优先级的线程执行。也有可能暂停后就马上再次执行。

notify(), wait()依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个！
这就是为什么notify(), wait()等函数定义在Object类，而不是Thread类中的原因。

Java中notify 和 notifyAll有什么区别？
notify()方法不能唤醒某个具体的线程，所以只有一个线程在等待的时候它才有用武之地。
而notifyAll()唤醒所有线程并允许他们争夺锁确保了至少有一个线程能继续运行。

多个线程之间如何协调？
wait()、notify()、notifyAll()：这三个方法用于协调多个线程对共享数据的存取，所以必须在同步语句块内使用。
wait方法要等待notify/notifyAll的线程释放锁后才能开始继续往下执行。

1 终止处于阻塞状态的线程
当线程由于被调用了sleep(), wait(), join()等方法而进入阻塞状态；若此时调用线程的interrupt()将线程的中断标记设为true。
由于处于阻塞状态，中断标记会被清除，同时产生一个InterruptedException异常。将InterruptedException放在适当的为止就能终止线程
2 中断运行状态下的线程
isInterrupted()是判断线程的中断标记是不是为true。当线程处于运行状态，并且我们需要终止它时；
可以调用线程的interrupt()方法，使用线程的中断标记为true，即isInterrupted()会返回true。此时，就会退出while循环。


java 中有两种线程：用户线程和守护线程。可以通过isDaemon()方法来区别它们：如果返回false，则说明该线程是“用户线程”；否则就是“守护线程”。
用户线程一般用户执行用户级任务，而守护线程也就是“后台线程”，一般用来执行后台任务。需要注意的是：Java虚拟机在“用户线程”都结束后会后退出。

为什么wait(), notify()和notifyAll()必须在同步方法或者同步块中被调用？
当一个线程需要调用对象的wait()方法的时候，这个线程必须拥有该对象的锁，
接着它就会释放这个对象锁并进入等待状态直到其他线程调用这个对象上的notify()方法。
同样的，当一个线程需要调用对象的notify()方法时，它会释放这个对象的锁，以便其他在等待的线程就可以得到这个对象锁。
由于所有的这些方法都需要线程持有对象的锁，这样就只能通过同步来实现，所以他们只能在同步方法或者同步块中被调用。

同步方法和同步块，哪个是更好的选择？
同步块是更好的选择，因为它不会锁住整个对象（当然你也可以让它锁住整个对象）。
同步方法会锁住整个对象，哪怕这个类中有多个不相关联的同步块，这通常会导致他们停止执行并需要等待获得这个对象上的锁。

volatile关键字在Java中有什么作用？
当我们使用volatile关键字去修饰变量的时候，所以线程都会直接读取该变量并且不缓存它。这就确保了线程读取到的变量是同内存中是一致的。

什么是死锁(Deadlock)？如何分析和避免死锁？
死锁是指两个以上的线程永远阻塞的情况，这种情况产生至少需要两个以上的线程和两个以上的资源。

一个线程池管理了一组工作线程，同时它还包括了一个用于放置等待执行的任务的队列。

volatile与synchronized
1）volatile本质是在告诉jvm当前变量在寄存器中的值是不确定的,需要从主存中读取,synchronized则是锁定当前变量,只有当前线程可以访问该变量,其他线程被阻塞住.
2）volatile仅能使用在变量级别,synchronized则可以使用在变量,方法.
3）volatile仅能实现变量的修改可见性,而synchronized则可以保证变量的修改可见性和原子性.
4）volatile不会造成线程的阻塞,而synchronized可能会造成线程的阻塞.

什么是原子操作？
检查数值、改变数值，以及可能发生的睡眠操作均作为单一的、不可分割的原子操作完成。

简述synchronized和java.util.concurrent.locks.Lock的异同？
主要相同点：Lock能完成synchronized所实现的所有功能
主要不同点：Lock有比synchronized更精确的线程语义和更好的性能。synchronized会自动释放锁，而Lock一定要求程序员手工释放，并且必须在finally从句中释放。

什么是竞态条件？你怎样发现和解决竞争？
当两个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。
在临界区中使用适当的同步就可以避免竞态条件。
界区实现方法有两种，一种是用synchronized，一种是用Lock显式锁实现。

你对线程优先级的理解是什么？
每一个线程都是有优先级的，一般来说，高优先级的线程在运行时会具有优先权，但这依赖于线程调度的实现，这个实现是和操作系统相关的(OSdependent)。
1、新建状态（New）：新创建了一个线程对象。
2、就绪状态（Runnable）：线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变得可运行，等待获取CPU的使用权。
3、运行状态（Running）：就绪状态的线程获取了CPU，执行程序代码。
4、阻塞状态（Blocked）：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：
（一）、等待阻塞：运行的线程执行wait()方法，JVM会把该线程放入等待池中。(wait会释放持有的锁) （二）、同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
（三）、其他阻塞：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。（注意,sleep是不会释放持有的锁）
5、死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。


什么是线程池？为什么要使用它？为什么使用Executor框架比使用应用创建和管理线程好？
创建线程要花费昂贵的资源和时间，如果任务来了才创建线程那么响应时间会变长，而且一个进程能创建的线程数有限。
为了避免这些问题，在程序启动的时候就创建若干线程来响应处理，它们被称为线程池，里面的线程叫工作线程。
Executor框架让你可以创建不同的线程池。比如单线程池，每次处理一个任务；数目固定的线程池或者是缓存线程池（一个适合很多生存期短的任务的程序的可扩展线程池）。

如何创建一个Java线程池？
Java通过Executors提供四种线程池

为什么wait和notify方法要在同步块中调用？
主要是因为Java API强制要求这样做，如果你不这么做，你的代码会抛出IllegalMonitorStateException异常。还有一个原因是为了避免wait和notify之间产生竞态条件。
最主要的原因是为了防止以下这种情况
// 等待者(Thread1)
while (condition != true) { // step.1
lock.wait() // step.4
}
// 唤醒者(Thread2)
condition = true; // step.2
lock.notify(); // step.3
在对之前的代码去掉 synchronized 块之后，如果在等待者判断 condition != true 之后而调用 wait() 之前，唤醒者**将 condition 修改成了 true 同时调用了 notify()
**的话，那么等待者在调用了 wait() 之后就没有机会被唤醒了。

Java多线程中调用wait() 和 sleep()方法有什么不同？
Java程序中wait和sleep都会造成某种形式的暂停，它们可以满足不同的需要。
wait存在于Object类中；sleep存在于Thread类中。
wait会让出CPU资源以及释放锁；sleep只会释放CPU资源。
wait只能在同步块中使用；sleep没这限制。
wait需要notify（或 notifyAll）唤醒，进入等锁状态；sleep到指定时间便会自动恢复到运行状态。

volatile关键字在Java中有什么作用？
volatile是一个特殊的修饰符，只有成员变量才能使用它。
在Java并发程序缺少同步类的情况下，多线程对成员变量的操作对其它线程是透明的。
volatile变量可以保证下一个读取操作会在前一个写操作之后发生。

什么是ThreadLocal变量？
ThreadLocal是Java里一种特殊的变量。
每个线程都有一个ThreadLocal就是每个线程都拥有了自己独立的一个变量，竞争条件被彻底消除了。它是为创建代价高昂的对象获取线程安全的好方法，比如你可以用ThreadLocal让SimpleDateFormat变成线程安全的，因为那个类创建代价高昂且每次调用都需要创建不同的实例所以不值得在局部范围使用它，如果为每个线程提供一个自己独有的变量拷贝，将大大提高效率。
首先，通过复用减少了代价高昂的对象的创建个数。 其次，你在没有使用高代价的同步或者不变性的情况下获得了线程安全。
线程局部变量的另一个不错的例子是ThreadLocalRandom类，它在多线程环境中减少了创建代价高昂的Random对象的个数。

什么是死锁(Deadlock)？
死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力作用，它们都将无法推进下去。

什么是乐观锁和悲观锁
悲观锁：假定会发生并发冲突，屏蔽一切可能违反数据完整性的操作。 乐观锁：假设不会发生并发冲突，只在提交操作时检查是否违反数据完整性。乐观锁不能解决脏读的问题。
1. 乐观锁是一种思想，具体实现是，表中有一个版本字段，第一次读的时候，获取到这个字段。处理完业务逻辑开始更新的时候，需要再次查看该字段的值是否和第一次的一样。如果一样更新，反之拒绝。之所以叫乐观，因为这个模式没有从数据库加锁。2.
悲观锁是读取的时候为后面的更新加锁，之后再来的读操作都会等待。这种是数据库锁乐观锁优点程序实现，不会存在死锁等问题。他的适用场景也相对乐观。阻止不了除了程序之外的数据库操作。

<%--框架--%>
Spring 是如何管理事务的？
spring的事务声明有两种方式，编程式和声明式。spring主要是通过“声明式事务”的方式对事务进行管理，即在配置文件中进行声明，通过AOP将事务切面切入程序，最大的好处是大大减少了代码量。

选择使用Spring框架的原因?
使用Spring： 第一是使用它的IOC功能，在解耦上达到了配置级别。 第二是使用它对数据库访问事务相关的封装。 第三就是各种其他组件与Spring的融合，在Spring中更加方便快捷的继承其他一些组件。


Bean 是如何被管理的？
在Spring框架中，一旦把一个bean纳入到Spring
IoC容器之中，这个bean的生命周期就会交由容器进行管理，一般担当管理者角色的是BeanFactory或ApplicationContext。认识一下Bean的生命周期活动，对更好的利用它有很大的帮助。
概括来说主要有四个阶段：实例化，初始化，使用，销毁。

Spring MVC的核心流程是什么样的？

用户请求发送到前端控制器DispatcherServlet。
前端控制器DispatcherServlet接收到请求后，DispatcherServlet会使用HandlerMapping来处理，HandlerMapping会查找到具体进行处理请求的Handler对象。
HandlerMapping找到对应的Handler之后，并不是返回一个Handler原始对象，而是一个Handler执行链，在这个执行链中包括了拦截器和处理请求的Handler。HandlerMapping返回一个执行链给DispatcherServlet。
DispatcherServlet接收到执行链之后，会调用Handler适配器去执行Handler。
Handler适配器执行完成Handler（也就是我们写的Controller）之后会得到一个ModelAndView，并返回给DispatcherServlet。
DispatcherServlet接收到Handler适配器返回的ModelAndView之后，会根据其中的视图名调用视图解析器。
视图解析器根据逻辑视图名解析成一个真正的View视图，并返回给DispatcherServlet。
DispatcherServlet接收到视图之后，会根据上面的ModelAndView中的model来进行视图中数据的填充，也就是所谓的视图渲染。
渲染完成之后，DispatcherServlet就可以将结果返回给用户了。


谈谈对Spring IoC的理解。
IoC不是什么技术，而是一种设计思想，它意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。
IoC能做什么：把创建和查找依赖对象的控制权交给了容器，由容器进行注入组合对象，所以对象与对象之间是松散耦合，这样也方便测试，利于功能复用，更重要的是使得程序的整个体系结构变得非常灵活；IoC很好的体现了面向对象设计法则之一 –
好莱坞法则；
IoC和DI是同一个概念的不同角度描述，“依赖注入”明确描述了“被注入对象依赖IoC容器配置依赖对象”。
在Spring里，BeanFactory提供了IoC容器最基本功能，而 ApplicationContext 则增加了更多支持企业级功能支持

选择使用Spring框架的原因?
使用Spring： 第一是使用它的IOC功能，在解耦上达到了配置级别。 第二是使用它对数据库访问事务相关的封装。
第三就是各种其他组件与Spring的融合，在Spring中更加方便快捷的继承其他一些组件。


<%--NIO--%>
介绍一下Java NIO中的Buffer、Channel和Selector的概念和作用。

<%--常用组件--%>
Dubbo是阿里巴巴提供的开源的SOA服务化治理的技术框架。
它最大的特点是按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度地松耦合）。
从服务模型的角度来看，Dubbo采用的是一种非常简单的模型，要么是提供方提供服务，
要么是消费方消费服务，所以基于这一点可以抽象出服务提供方（Provider）和服务消费方（Consumer）两个角色。

Redis，一个开源的 key-value，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
</body>
</html>
