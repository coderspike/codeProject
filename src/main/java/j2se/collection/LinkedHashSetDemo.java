package j2se.collection;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public void LinkedHashSetDemo() {
        Set set = new LinkedHashSet();
        /*
        1、继承关系
        public class LinkedHashSet<E>
        extends HashSet<E>
        implements Set<E>, Cloneable, java.io.Serializable

        LinkedHashSet继自HashSet，但是内部的map是使用LinkedHashMap构造的，并且accessOrder为false，
        使用查询顺序。所以LinkedHashSet遍历的顺序就是插入顺序。

        */
    }
}
