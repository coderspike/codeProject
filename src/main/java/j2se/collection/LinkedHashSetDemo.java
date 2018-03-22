package j2se.collection;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public void LinkedHashSetDemo() {
        // TODO: 2018-03-22 LinkedHashSet
        Set set = new LinkedHashSet();
        //底层使用LinkedHashMap作为存储结构
//        HashSet(int initialCapacity, float loadFactor, boolean dummy) {
//            map = new LinkedHashMap<>(initialCapacity, loadFactor);
//        }
//        1、底层存储基于LinkedHashMap实现，内部使用双向链表存储元素，所以保证了元素的顺序性。
//        2、基于key的hash值存储，同样的对象hash值相同，所以元素不可重复，但是可以为null，可以快速查找是否包含某个元素。

    }
}
