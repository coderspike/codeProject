package j2se.collection;

/**
 * 认真,简约
 */
public class CollectionDemo {

    /*
     Collection是最基本的集合接口，一组Collection代表一组Object

     Collection接口是List、Set等集合高度抽象出来的接口，它包含了这些集合的基本操作，主要又分为两大部分：List和Set。

     Collection
      --List
      --Set

      Map
      --HashMap
      --TreeMap
      --LinkedHashMap
     */

     /*
        AbstractCollection 类： Collection接口的骨架实现类，最小化实现了Collection接口所需要实现的工作量
     */

     /*
     一个实现了Comparable接口的类，可以让其自身的对象和其他对象进行比较。
     也就是说，同一个类的两个对象之间要想比较，对应的类就要实现Comparable接口，并实现compareTo()方法

     在一些情况下，你不希望修改一个原有的类，但是你还想让他可以比较，Comparator接口可以实现这样的功能。通过使用Comparator接口，你可以针对其中特定的属性/字段来进行比较。比如，当我们要比较两个人的时候，我可能通过年龄比较、也可能通过身高比较。
     这种情况使用Comparable就无法实现（因为要实现Comparable接口，其中的compareTo方法只能有一个，无法实现多种比较）。

     如何实现集合排序?
     你可以使用有序集合，如 TreeSet 或 TreeMap，你也可以使用有顺序的的集合，如 list，然后通过 Collections.sort() 来排序。
      */

}
