package j2se.collection;

import java.util.Arrays;

/**
 * 数组相关
 */
public class ArrayDemo {
    // TODO: 2018-03-10 array的父类是：class java.lang.Object
    int[] array = new int[10];
    Person person = new Person("name");
    // TODO: 2018-03-10 数组复制
    Person[] persons1 = new Person[]{person};
    Person[] persons2 = Arrays.copyOf(persons1, persons1.length);

    class Person {
        String userName;

        public Person() {
        }

        public Person(String userName) {
            this.userName = userName;
        }
    }

}
