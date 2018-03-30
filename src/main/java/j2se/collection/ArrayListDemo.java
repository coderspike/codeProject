package j2se.collection;

import java.util.*;

/**
 * ArrayList
 */
public class ArrayListDemo {
    public static void ArrayList() {

        /*
        1、这是arrayList的继承关系
        public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable

        2.AbstractList提供了相关的添加、删除、修改、遍历等功能。
        public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>

        Cloneable 只是一个接口没有实质内容，应该只是标记作用

        3、ArrayList包含了两个重要的对象：elementData 和 size。
           elementData 是"Object[]类型的数组"
           size 则是动态数组的实际大小
        */

        /*
        多线程解决方案：
        ArrayList不是线程安全的，只能在单线程环境下，
        多线程环境下可以考虑用Collections.synchronizedList(List list)方法返回一个线程安全的ArrayList对象，
        也可以使用concurrent并发包下的CopyOnWriteArrayList类。
        */

        /*
        扩容方法：
       //扩容。确保至少能容纳最小容量参数指定的元素个数。
       //这是动态扩容的精髓，ArrayList的奥秘一览无余
       private void grow(int minCapacity)
       {
        int oldCapacity = elementData.length;
        //得到数组的旧容量，进行oldCapacity + (oldCapacity >> 1)，将oldCapacity右移一位，相当于oldCapacity/2
        //这样的结果便是将新数组的容量扩展到原来数组的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //判断新数组的容量够不够，够了就直接使用这个大小创建新的数组
        //不够就将数组大小设置为需要的大小
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //在判断有没有超过上面的最大容量限制，超出限制就调hugeCapacity（）方法进行处理
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        //将原来数组的值copy新数组中去，ArrayList的引用指向新数组
        //这儿会新创建数组，如果数据量很大，重复的创建数组，会影响效率
        //因此最好在合适的时候通过构造方法指定默认的capaticy大小
        elementData = Arrays.copyOf(elementData, newCapacity);
        }
         */

        /*
        List 和 Set都是继承自 Collection接口
        ArrayList相当于动态数组，默认容量为10
        list取值快，插入慢，支持随机访问,线程不安全
        添加或删除一个元素需要移动数组中的其他元素,所以不适合频繁操作。这是ArrayList最大的缺点。当数据量大的时候很耗性能。
        适用于频繁的数据读取
        允许加入null元素，可以添加重复元素。
        */
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(null);
        list.get(0);


        // TODO: 2018-03-10 这是arraylist的初始化方法，可见底层是一个数组,默认长度为10
        /*
        if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
        }
        */

        // TODO: 2018-03-10 转数组
        String[] array = new String[list.size()];
        list.toArray(array);

        // TODO: 2018-03-10 删除元素，面试经常考，其实是用迭代器调用remove
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            it.remove();
        }

        // TODO: 2018-03-10 将list转化为set
        Set<Integer> set = new HashSet<Integer>(list);

        // TODO: 2018-03-10 拷贝list
        //方法一
        ArrayList<Integer> dstList = new ArrayList<Integer>(list);
        //方法二
        ArrayList<Integer> dstList2 = new ArrayList<Integer>(list.size());
        Collections.copy(dstList, list);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);

        Set<Integer> set = new HashSet<Integer>(list);
        list.clear();
        list.addAll(set);

        // TODO: 2018-03-11 删除arraylist中的重复元素
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            System.out.println(next);
        }

        // TODO: 2018-03-11 排序
        ArrayList list2 = new ArrayList();
        list2.add("92.8");
        list2.add("68.9");
        list2.add("168.61");
        list2.add("242");
        list2.add("317");
        list2.add("105");

        // 字符串排序
        Collections.sort(list2);
        System.out.println(list2.toString()); // [105, 168.61, 242, 317, 68.9, 92.8]
        Collections.sort(list2, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return new Double((String) o1).compareTo(new Double((String) o2));
            }
        });
        System.out.println(list2.toString()); // [68.9, 92.8, 105, 168.61, 242, 317]

        // TODO: 2018-03-11 排序 使用comparable接口
        List<A> list1 = new ArrayList<A>();
        list1.add(new A(3));
        list1.add(new A(1));
        list1.add(new A(2));
        Collections.sort(list1); // 因为类A实现了Comparable接口，所以可以直接进行排序

        // TODO: 2018-03-11 清空
        //Clear() 将list中每一个元素都设置为null
        //RemoveAll(Collection c) 调用contains后remove 效率较低
    }

    //排序 使用Comparable接口
    static class A implements Comparable<A> {
        int a;

        A(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return Integer.toString(a);
        }

        public int compareTo(A o) {
            return (a < o.a) ? -1 : ((a == o.a) ? 0 : 1);
        }
    }
}
