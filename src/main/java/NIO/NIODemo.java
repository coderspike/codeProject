package NIO;

/**
 * NIO
 */
public class NIODemo {
    /*
        NIO（Non-blocking I/O，在Java领域，也称为New I/O），是一种同步非阻塞的I/O模型
        NIO和IO有相同的作用和目的，但实现方式不同，NIO主要用到的是块[每一个操作都在一步中产生或消费一个数据块]，所以NIO的效率要比IO高很多。
        在Java API中提供了两套NIO，一套是针对标准输入输出NIO，另一套就是网络编程NIO。
        IO是以流的方式处理数据，而NIO是以块的方式处理数据。
        Buffer和Channel是标准NIO中的核心对象

        Buffer:
        Buffer是一个对象，它包含一些要写入或读出的数据。在NIO中，数据是放入buffer对象的，
        而在IO中，数据是直接写入或者读到Stream对象的。
        应用程序不能直接对 Channel 进行读写操作，而必须通过 Buffer 来进行，即 Channel 是通过 Buffer 来读写数据的。

        使用 Buffer 读写数据一般遵循以下四个步骤：
        1 写入数据到 Buffer；
        2 调用 flip() 方法；
        3 从 Buffer 中读取数据；
        4 调用 clear() 方法或者 compact() 方法。

        一旦要读取数据，需要通过 flip() 方法将 Buffer 从写模式切换到读模式
        一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。
        有两种方式能清空缓冲区：调用 clear() 或 compact() 方法。
        clear() 方法会清空整个缓冲区。compact() 方法只会清除已经读过的数据。
        任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。

        关于Channel:
        Channel是一个对象，可以通过它读取和写入数据。可以把它看做IO中的流。但是它和流相比还有一些不同：
        Channel是双向的，既可以读又可以写，而流是单向的；
        Channel可以进行异步的读写；
        对Channel的读写必须通过buffer对象。

        在Java NIO中Channel主要有如下几种类型：
        FileChannel：从文件读取数据的
        DatagramChannel：读写UDP网络协议数据
        SocketChannel：读写TCP网络协议数据
        ServerSocketChannel：可以监听TCP连接

        NIO中的读写:
        IO中的读和写，对应的是数据和Stream，NIO中的读和写，则对应的就是通道和缓冲区。
        NIO中从通道中读取：创建一个缓冲区，然后让通道读取数据到缓冲区。
        NIO写入数据到通道：创建一个缓冲区，用数据填充它，然后让通道用这些数据来执行写入。

        如果从文件读取数据的话，需要以下三步：
        从FileInputStream获取Channel；
        创建Buffer；
        从Channel读取数据到Buffer。

     */
}
