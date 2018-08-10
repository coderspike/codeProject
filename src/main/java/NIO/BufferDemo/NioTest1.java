package NIO.BufferDemo;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/*
Buffer本身就是块内存，底层实现上，它实际上是个数组。数据的读、写都是通过Buffer来实现的。
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}