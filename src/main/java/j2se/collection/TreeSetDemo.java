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

        TreeSet跟HashSet一样，内部都使用Map，HashSet内部使用的是HashMap，但是TreeSet使用的是NavigableMap。

        */

    }
}
