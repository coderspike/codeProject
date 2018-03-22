package j2se.collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public void hashSet() {
        // TODO: 2018-03-22 HashSet是基于HashMap实现的，可以看作是对HashMap的封装
        Set set = new HashSet();
        // TODO: 2018-03-22 构造方法
//          public HashSet() {
//            map = new HashMap<>();
//        }
        //1、由于底层基于HashMap实现，内部使用基于哈希表的数组+链表方式存储，所以不保证元素的存取顺序。
        //2、基于key的hash值存储，同样的对象hash值相同，所以元素不可重复，但是可以为null，可以快速查找是否包含某个元素。

    }
}
