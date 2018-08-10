package NIO.BufferDemo;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
写入文件
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] messages = "hello world welcome, nihao".getBytes();
        for (byte message : messages) {
            byteBuffer.put(message);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);

    }
}