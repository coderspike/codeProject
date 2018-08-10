package j2se.collection;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet
 */
public class TreeSetDemo {
    public void treeSet() {

        Set set = new TreeSet();

        /*
        1 继承关系
        public class TreeSet<E> extends AbstractSet<E>
        implements NavigableSet<E>, Cloneable, java.io.Serializable

        public interface NavigableSet<E> extends SortedSet<E>

        与 HashSet 是基于 HashMap 实现一样，TreeSet 同样是基于 TreeMap 实现的

        */

    }
}
