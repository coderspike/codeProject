package j2se.collection;

import java.util.*;

/**
 * 关于List
 */
public class ArrayListDemo {
    /**
     * ArrayList
     */
    public static void ArrayList() {
        //public interface List<E> extends Collection<E>
        //public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
        //public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>
        //public abstract class AbstractCollection<E> implements Collection<E>
        //List 和 Set都是继承自 Collection接口，接口居然可以继承O__O "…
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        //允许加入null元素，可以添加重复元素。
        list.add(null);
        list.get(0);
        // list取值快，插入慢，支持随机访问,线程不安全
        // 添加或删除一个元素需要移动数组中的其他元素。这是ArrayList最大的缺点。当数据量大的时候很耗性能。
        // 适用于频繁的数据读取
        // TODO: 2018-03-10 这是arraylist的初始化方法，可见底层是一个数组,默认长度为10
//      if (initialCapacity > 0) {
//            this.elementData = new Object[initialCapacity];
//      }

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
