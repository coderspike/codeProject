package fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * fastdfs客户端连接池
 */
public class FdfsClientPool {

    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(FdfsClientPool.class);
    //最大连接数,可以写配置文件
    private static int size = 20;
    //被使用的连接，该map本身线程安全
    private static ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
    //空闲的连接,该队列本身线程安全
    private static ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;

    static {
        init(size);
    }

    /**
     * 初始化客户端
     */
    private static void initClientGlobal() {
        ClassPathResource cpr = new ClassPathResource("fdfs_client.conf");
        try {
            ClientGlobal.init(cpr.getClassLoader().getResource("fdfs_client.conf").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化连接池
     *
     * @param sizeNum
     */
    public static void init(int sizeNum) {
        if (busyConnectionPool != null) {
            busyConnectionPool.clear();
        } else {
            busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
        }
        if (idleConnectionPool != null) {
            idleConnectionPool.clear();
        } else {
            idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
        }
        initClientGlobal();
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            //只需要一个tracker server连接
            trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient1 storageClient1 = null;
            for (int i = 0; i < sizeNum; i++) {
                storageClient1 = new StorageClient1(trackerServer, storageServer);
                idleConnectionPool.add(storageClient1);
                logger.info("初始化fastdfs连接池::" + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭trackerServer连接
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从连接池中取出连接
     *
     * @param waitTime 毫秒
     * @return
     */
    public static StorageClient1 getConnection(int waitTime) {
        logger.info("从连接池中获取连接");
        StorageClient1 storageClient1 = null;
        try {
            //获取该队列头部，如果没有可用元素则等待指定时间
            storageClient1 = idleConnectionPool.poll(waitTime, TimeUnit.SECONDS);
            if (storageClient1 != null) {
                //key,value
                busyConnectionPool.put(storageClient1, new Object());
            }
        } catch (InterruptedException e) {
            storageClient1 = null;
            e.printStackTrace();
        }
        return storageClient1;
    }

    /**
     * 客户端用完之后回收连接
     *
     * @param storageClient1
     */
    public static void remove(StorageClient1 storageClient1) {
        Object flay = busyConnectionPool.remove(storageClient1);
        if (flay != null) {
            idleConnectionPool.add(storageClient1);
        }
    }

    /**
     * 如果连接无效则抛弃，新建连接来补充到池里
     *
     * @param storageClient1
     */
    public static void drop(StorageClient1 storageClient1) {
        if (busyConnectionPool.remove(storageClient1) != null) {
            TrackerServer trackerServer = null;
            TrackerClient trackerClient = new TrackerClient();
            try {
                trackerServer = trackerClient.getConnection();
                StorageClient1 newStorageClient1 = new StorageClient1(trackerServer, null);
                idleConnectionPool.add(newStorageClient1);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (trackerServer != null) {
                    try {
                        trackerServer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
