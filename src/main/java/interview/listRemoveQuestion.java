package interview;

import java.util.ArrayList;
import java.util.List;

public class listRemoveQuestion {
    public static void main(String[] args) {
//        listRemoveQuestion.test1();
        listRemoveQuestion.test3();

    }

    public static void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        for (int i = 0; i < list.size(); i++) {
            if (2 == list.get(i)) {
                list.remove(i);
                //结果显示只删除了一个2，另一个2被遗漏了，
                //原因是：删除了第一个2后，集合里的元素个数减1，后面的元素往前移了1位，导致了第二个2被遗漏了。
            }
            System.out.println(list.get(i));
        }
        System.out.println("list=" + list.toString());
    }

    public static void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        for (int value : list) {
            if (2 == value) {
                //这样删除是不行的
                list.remove(value);
            }
            System.out.println(value);
        }
        System.out.println("list=" + list.toString());
    }


    /**
     * 因为执行stringList.remove(2)后，
     * list.size()就-1为4了，我们原来要移除的最后一个位置的数据移动到了第3个位置上，自然就造成了越界。
     * 这个错误提醒我们：使用remove()的方法时，要先从大到小的位置移除。
     * 当然如果你知道具体的对象，直接移除remove(对象)更稳妥。
     * Java系统中如果找不到准确的对象，会自动向上升级.
     */
    public static void test3() {
        List<String> stringList = new ArrayList<>();//数据集合
        List<Integer> integerList = new ArrayList<>();//存储remove的位置
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        stringList.add("e");
        integerList.add(2);
        integerList.add(4);//此处相当于要移除最后一个数据

        for (Integer i : integerList) {
            stringList.remove(i);
        }
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}

