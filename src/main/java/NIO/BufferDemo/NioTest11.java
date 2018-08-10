package NIO.BufferDemo;

/**
 * 关于Buffer的Scattering与Gathering
 */
public class NioTest11 {
//    public static void main(String[] args) throws IOException {
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        InetSocketAddress address = new InetSocketAddress(8899);
//        serverSocketChannel.socket().bind(address);
//        int messageLength = 2 + 3 + 4;
//        ByteBuffer[] buffers = new ByteBuffer[3];
//        buffers[0] = ByteBuffer.allocate(2);
//        buffers[1] = ByteBuffer.allocate(3);
//        buffers[2] = ByteBuffer.allocate(4);
//        SocketChannel socketChannel = serverSocketChannel.accept();
//        while (true) {
//            int bytesRead = 0;
//            while (bytesRead < messageLength) {
//                // 数组类型的读
//                long r = socketChannel.read(buffers);
//                bytesRead += r;
//                System.out.println("bytesRead: " + bytesRead);
//                Stream.of(buffers)
//                        .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
//                        .forEach(System.out::println);
//            }
//            Stream.of(buffers).forEach(Buffer::flip);
//            long bytesWritten = 0;
//            while (bytesWritten < messageLength) {
//                long r = socketChannel.write(buffers);
//                bytesWritten += r;
//            }
//            Stream.of(buffers).forEach(Buffer::clear);
//            System.out.println("bytesRead:  " + bytesRead + "， byteWritten: " + bytesWritten + ", messageLength: " + messageLength);
//        }
//    }
    /*
    这2个命令都可以进行刚才的程序测试。
    nc localhost 8899
    hellowor
    hellowor
    回车也算一个字节，所以输入hellowor后，程序马上回写了数据。控制台输出：
    bytesRead: 9
    position: 2, limit: 2
    position: 3, limit: 3
    position: 4, limit: 4
    bytesRead:  9， byteWritten: 9, messageLength: 9
     */
}