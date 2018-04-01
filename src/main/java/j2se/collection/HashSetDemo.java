package j2se.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {

        /*
          HashSet的继承关系：
          public class HashSet<E>
          extends AbstractSet<E>
          implements Set<E>, Cloneable, java.io.Serializable

          其中 AbstractSet 提供 Set 接口的骨干实现，从而最大限度地减少了实现此接口所需的工作
          public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>

          HashSet实现Set接口，不允许有重复[与List最大的不同，另外就是无序]，在对象存入set要先确保对象重写equals()和hashCode()方法[没实现的话走的是默认方法]。
          HashSet是基于HashMap实现的。

          不能保证存取顺序，因为HashSet内部使用的是HashMap,存储时是通过hash函数得到的下标。
          HashSet内部有一个HashMap的成员变量，我们在 HashSet 的构造函数中将其初始化，
          默认情况下采用的是 initial capacity为16，load factor 为 0.75。
          HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，
          所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false

          线程不同步：需要多线程访问它的话，可以用 Collections.synchronizedSet 方法来包装它
          Set s = Collections.synchronizedSet(new HashSet(...));
         */

         /*
            基本操作

            PRESENT是用来造一个假的value来用的。

            private transient HashMap<E,Object> map;
            // Dummy value to associate with an Object in the backing Map
            private static final Object PRESENT = new Object();

            public boolean add(E e) {
                return map.put(e, PRESENT)==null;
            }
            public boolean remove(Object o) {
                return map.remove(o)==PRESENT;
            }
            public boolean contains(Object o) {
                return map.containsKey(o);
            }
            public int size() {
                return map.size();
        }
         */

        // TODO: 不能保证顺序
        Set hs = new HashSet();
        hs.add("世界军事");
        hs.add("兵器知识");
        hs.add("舰船知识");
        hs.add("汉和防务");
        System.out.println(hs);
        Iterator it = hs.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            //[世界军事, 汉和防务, 兵器知识, 舰船知识]
        }

    }


}
