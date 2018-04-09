package NIO.SelectorDemo;

public class SelectorDemo {
    /*
    Selector（选择器）是Java NIO的一个组件，能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备。
    这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。仅用单个线程来处理多个Channels的好处是，
    只需要更少的线程来处理通道。对于操作系统来说，线程之间上下文切换的开销很大，而且每个线程都要占用系统的一些资源。因此，使用的线程越少越好。

    创建:
    通过调用Selector.open()方法创建一个Selector，例如：

    Selector selector = Selector.open();
    Selector里持有3个集合:

    key set 注册在Selector的所有通道。
    selected-key set 准备好进行操作的通道。
    cancelled-key set 准备取消的通道。



     */
}
