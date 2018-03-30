package j2se.collection;


import java.util.LinkedList;
import java.util.List;

/**
 * LinkList
 */
public class LinkListDemo {

    public void LinkListDemo() {
        /*
         public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>,Deque<E>,
         Cloneable, java.io.Serializable

         基于双向链表实现，链表无容量限制，不需要扩容，但双向链表本身使用了更多空间，也需要额外的链表指针操作。
         在链表中添加或删除元素很快，只需要O(1)的时间复杂度，适用于频繁增删的场景。在随机访问的方面不如Arraylist
         线程不安全,arrayList基于数组，linkList基于双向链表
         可添加null，可重复
         有序性
         */

        /*
           内部类 实现双向链表结构 header表示链表的表头，Entry为节点对象
           private static class Entry<E> {
            E element;          //元素节点
            Entry<E> next;      //下一个元素
            Entry<E> previous;  //上一个元素
            Entry(E element, Entry<E> next, Entry<E> previous) {
                this.element = element;
                this.next = next;
                this.previous = previous;
                }
            }
        */

        List list = new LinkedList();
        list.add(1);
        list.add(1);
        list.add(null);

    }
}
