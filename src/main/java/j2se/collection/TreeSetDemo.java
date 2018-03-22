package j2se.collection;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet
 */
public class TreeSetDemo {
    public void treeSet() {

        Set set = new TreeSet();
        // 1、底层存储基于TreeMap实现，内部使用红黑树结构表存储元素，所以保证了元素的顺序性。
        // 2、元素不可为null。
        // 3、遍历时不支持随机访问，只能通过迭代器和for-each遍进行遍历。
    }
}
