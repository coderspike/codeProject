package j2se.collection;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {

        /*
            数据结构：
            HashMap是数组+链表+红黑树
            []-->O-->O(链表长度大于8时转换为红黑树)
            []-->O
            []
            []

            static class Node<K,V> implements Map.Entry<K,V>  {
            final int hash; //用来定位数组索引位置
            final K key;
            V value;
            Node<K,V> next; //链表的下一个node(为了解决hash碰撞的)

            Node(int hash, K key, V value, Node<K,V> next) { ... }
            public final K getKey(){ ... }
            public final V getValue() { ... }
            public final String toString() { ... }
            public final int hashCode() { ... }
            public final V setValue(V newValue) { ... }
            public final boolean equals(Object o) { ... }
            }
            Node是HashMap的一个内部类，实现了Map.Entry接口，本质是就是一个映射(键值对)。上图中的每个圆点就是一个Node对象。
            hashMap解决hash碰撞的问题用链地址法
            HashMap存储着Entry(hash, key, value, next)对象。
        */

        /*
            put函数大致的思路为：
            对key的hashCode()做hash，然后再计算index;
            如果没碰撞直接放到bucket里；
            如果碰撞了，以链表的形式存在buckets后；
            如果碰撞导致链表过长(大于等于TREEIFY_THRESHOLD)，就把链表转换成红黑树；
            如果节点已经存在就替换old value(保证key的唯一性)
            如果bucket满了(超过load factor*current capacity)，就要resize。
            你知道get和put的原理吗？equals()和hashCode()的都有什么作用？
            通过对key的hashCode()进行hashing，并计算下标( n-1 & hash)，从而获得buckets的位置。
            如果产生碰撞，则利用key.equals()方法去链表或树中去查找对应的节点
        */

        /*
            扩容：
            void resize(int newCapacity) { //传入新的容量
            Entry[] oldTable = table; //引用扩容前的Entry数组
            int oldCapacity = oldTable.length;
            if (oldCapacity == MAXIMUM_CAPACITY) { //扩容前的数组大小如果已经达到最大(2^30)了
                threshold = Integer.MAX_VALUE; //修改阈值为int的最大值(2^31-1)，这样以后就不会扩容了
                return;
            }

            Entry[] newTable = new Entry[newCapacity]; //初始化一个新的Entry数组
            transfer(newTable); //！！将数据转移到新的Entry数组里
            table = newTable; //HashMap的table属性引用新的Entry数组
            threshold = (int)(newCapacity * loadFactor);//修改阈值
            }
        */

        /*
            1、HashMap的继承关系
            public class HashMap<K,V> extends AbstractMap<K,V>
            implements Map<K,V>, Cloneable, Serializable

            public abstract class AbstractMap<K,V> implements Map<K,V>  //[提供了骨架方法(这也是抽象类的作用，提供一些默认实现)]

            Map接口有两个基本实现：
            --| HashMap 不能保证存取顺序，HashMap中使用键对象来计算hashcode值(内部是数组加链表)
            --| TreeMap 能保证存取顺序(内部是红黑树)
            --| LinkedHashMap LinkedHashMap是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，
            先得到的记录肯定是先插入的，也可以在构造时带参数，按照访问次序排序。
            --| HashTable
        */

        /*
             线程不安全：因为多线程环境下，使用HashMap进行put操作会引起死循环
             Collections的synchronizedMap方法使HashMap具有线程安全的能力，或者使用ConcurrentHashMap
             HashTable容器使用synchronized来保证线程安全效率较低
             不能保证存储的顺序性
             hashTable是遗留类，与hashMap类似，线程安全[HashTable实现线程同步是加了synchronized]
             在key未冲突的情况下，搜索的时间复杂度为o(1)
             默认容量16 加载因子0.75
             散列表（Hash table，也叫哈希表），是根据关键字（Key value）而直接访问在内存存储位置的数据结构。
             HashMap底层是个哈希表，使用拉链法解决冲突
             扩容是一个特别耗性能的操作，所以当程序员在使用HashMap的时候，估算map的大小，初始化的时候给一个大致的数值，避免map进行频繁的扩容。
        */

        // TODO: 2018-03-30 不能包含重复的key，因为通过key的hash决定存储位置，如果key相同则返回false不存入。
        Map map = new HashMap<String, String>();
        // 不能包含重复键,后面的会覆盖前面的
        map.put(1, 1);
        map.put(1, 3);
        map.put(2, 2);
        // 允许key为null 其实就是放在了第0个位置，至多一个
        map.put(null, 2);

        // TODO: 2018-03-11 遍历HashMap
        // Set<Map.Entry<K, V>> entrySet();
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(value);
        }

        // TODO: 2018-03-11 转化成数组
        //HashMap内部存储的数据是无序的，这是因为HashMap内部的数组的下表是根据hash值算出来的
        Object[] objects = map.entrySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }

    /**
     * map排序，按照value数值倒序排序
     */
    public static Map<String, Integer> sortMap(Map<String, Integer> oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg1.getValue() - arg0.getValue();
            }
        });
        Map<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }
}
