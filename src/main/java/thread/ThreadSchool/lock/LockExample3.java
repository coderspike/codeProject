package thread.ThreadSchool.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockExample3 {

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    /*
    一个ReentrantReadWriteLock实例包含一个ReentrantReadWriteLock.ReadLock实例和一个ReentrantReadWriteLock.WriteLock实例。
    通过readLock()和writeLock()方法可分别获得读锁实例和写锁实例，并通过Lock接口提供的获取锁方法获得对应的锁。
    读写锁的锁定规则如下：
    获得读锁后，其它线程可获得读锁而不能获取写锁
    获得写锁后，其它线程既不能获得读锁也不能获得写锁
     */

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            readLock.unlock();
        }
    }

    class Data {

    }
}
