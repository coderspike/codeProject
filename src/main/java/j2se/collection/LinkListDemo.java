package j2se.collection;


import java.util.LinkedList;
import java.util.List;

/**
 * LinkListDemo
 */
public class LinkListDemo {
    public void LinkListDemo() {
        // TODO: 2018-03-10  继承关系
        //public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>,Deque<E>, Cloneable, java.io.Serializable
        //双向链表,在链表中添加或删除元素很快，只需要O(1)的时间复杂度
        List list = new LinkedList();
        list.add(1);
        list.add(1);
        //可添加null
        list.add(null);
    }
}
