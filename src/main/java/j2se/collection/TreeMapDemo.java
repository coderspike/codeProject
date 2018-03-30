package j2se.collection;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

    public void TreeMapDemo() {
        Map m = new TreeMap();
        /*
        1、继承关系
        public class TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, java.io.Serializable
        public interface NavigableMap<K,V> extends SortedMap<K,V>

        TreeMap继承自AbstractMap,内部使用红黑树(一种二叉树)结构实现元素存储。
        元素有序
        不允许Null的Key
        可以插入Null的Value
        Key重复的话会覆盖原来的Value
        线程不安全
        如果不需要有序性，建议使用hashMap
        */

    }


}
