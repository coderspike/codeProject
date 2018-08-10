package j2se.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

         /*
             1、继承关系
             public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>

             LinkedHashMap直接继承自HashMap，最大的不同是保证了存储的顺序性
             其实整体和HashMap类似，只不过通过节点中的before和after等属性维护了顺序。形成了双向链表。
             线程不安全
             同样是使用链表法来解决hash碰撞问题
             相比hashMap不同的就是节点的结构

             LinkedHashMap有个内部类Entry，这个Entry就是链表中的节点，继承自HashMap.Node，
             多出了2个属性before和after，所以LinkedHashMap内部链表的节点是双向的
             static class Entry<K,V> extends HashMap.Node<K,V> {
                Entry<K,V> before, after;
                Entry(int hash, K key, V value, Node<K,V> next) {
                super(hash, key, value, next);
                }
             }

             LinkedHashMap还有两个重要的属性head，tail，这2个属性用于存储插入的节点，形成一个双向链表：
             // 首节点
             transient LinkedHashMap.Entry<K,V> head;
             // 尾节点
             transient LinkedHashMap.Entry<K,V> tail;
         */
        Map<String, Integer> map = new LinkedHashMap<String, Integer>(5);
        map.put("java", 1);
        map.put("golang", 2);
        map.put("python", 3);
        map.put("ruby", 4);
        map.put("scala", 5);
        System.out.println(map);


    }
}
