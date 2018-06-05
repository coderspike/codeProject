package j2se.collection;

/*
ConcurrentHashMap是线程安全的hashmap，在多线程的情况下使用ConcurrentHashMap既能保证线程的安全，
又能保证性能的最优，hashtable虽然也是线程安全的，可是在多线程的情况下效率低下。

ConcurrentHashMap在发生冲突的时候和hashmap一样采用链地址法，可是ConcurrentHashMap又多了一种数据结构叫做Segment，
每次线程访问的时候只锁其对应的segment，不用的segment可以并发执行。
 */
public class ConcurrentHashMapDemo {
    /*
    数据结构如下：

    Segments[]:segment  [] [] [] [] [] [] []
                            |
                            |
    HashEntry[]:table   [] [] [] [] [] [] []
                         |  |
                         |  |
                        []  [] (HashEntry[key,value,next,hash])


   static final class Segment<K,V> extends ReentrantLock implements Serializable {
        transient volatile HashEntry<K,V>[] table; // 桶, 除了类型, 其他跟HashMap里的table一样
        transient int count;     // HashEntry的总个数, 对应HashMap里的size
        transient int modCount;  // 同HashMap
        transient int threshold; // 同HashMap, 用于rehash, rehash时只会改变table的大小, segments大小确定之后就不会再变了
    }


    可以看出ConcurrentHashMap有点像把HashTable又包了一层, 把table放到了segments里,
    这样同步锁是在每一个segment里的, 只要多个修改操作发生在不同的段上, 它们就可以并发进行.
    我们为了区分, 把每个元素segment成为段(有的文章里称作桶), 把segment里面的table的单个元素成为桶.

    segments元素继承自reentrantLock，可以方便的进行锁操作，分段锁。
     */
}
