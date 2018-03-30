package j2se.collection;

/**
 * set 接口讲解
 * 怕，你就输一辈子。
 * 多问为什么，触类旁通的思考。
 * 看十遍不如实践一遍，边实践边理解边记录。
 */
public class SetDemo {
    /*
    public interface Set<E> extends Collection<E>
    public interface Collection<E> extends Iterable<E>
    set接口---》Collection接口---》Iterable接口

    Set接口通常表示一个集合，只是一个接口，其中的元素不允许重复，常用实现类有HashSet、LinkedHashSet和TreeSet。
    Set是Itreable---》Collection---》Set的接口（无序）。
    Set集合中的元素是唯一的，不可重复（取决于hashCode和equals方法），也就是说具有唯一性。
    Set集合中元素不保证存取顺序，并不存在索引。
    Set只是Map的一个马甲，主要逻辑都交给Map实现。

    看到array，就要想到角标。
    看到link，就要想到first，last。
    看到hash，就要想到hashCode，equals。
    看到tree，就要想到两个接口。Comparable，Comparator。

    hashCode:
    public native int hashCode();
    它是一个本地方法，它的实现与本地机器有关。这里我们暂且认为他返回的是对象存储的物理位置（实际上不是，这里写是便于理解）。
    当我们向一个集合中添加某个元素，集合会首先调用 hashCode 方法，这样就可以直接定位它所存储的位置，若该处没有其他元素，
    则直接保存。若该处已经有元素存在，就调用 equals 方法来匹配这两个元素是否相同，相同则不存，不同则散列到其他位置

    整个处理流程是：
    1、判断两个对象的 hashcode 是否相等，若不等，则认为两个对象不等，完毕，若相等，则比较 equals。
    2、若两个对象的 equals 不等，则可以认为两个对象不等，否则认为他们相等。

    ---| Itreable 接口 实现该接口可以使用增强for循环
                ---| Collection 描述所有集合共性的接口
                    ---| List接口     可以有重复元素的集合
                            ---|  ArrayList
                            ---|  LinkedList
                    ---| Set接口      不可以有重复元素的集合
                            ---| HashSet 线程不安全，存取速度快。底层是以哈希表（hashMap）实现的。
                            ---| LinkedHashSet 带有双向链表的哈希表结构，线程不安全，保持存取顺序，保持了查询速度较快特点。(本质上基于LinkedHashMap实现)
                            ---| TreeSet 红-黑树的数据结构，线程不安全,默认对元素进行自然排序（String）
    */

}
