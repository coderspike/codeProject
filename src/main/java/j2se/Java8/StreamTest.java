package j2se.Java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        User[] people = {
                new User("Mohamed", 69),
                new User("Doaa", 25),
                new User("Malik", 6)};
        Stream<User> stream = Stream.of(people);
        stream.forEach(p -> System.out.println(p.getName()));

        List<User> list = new ArrayList<User>();
        User user1 = new User("one", 11, "小学");
        User user2 = new User("two", 11, "小学");
        User user3 = new User("three", 16, "初中");
        User user4 = new User("four", 17, "高中");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        Map<String, List<User>> userGroupMap = list.stream().collect(Collectors.groupingBy(User::getSchool));
        System.out.println(userGroupMap);

        String str = "1,2,3,4,10,11,9,66,222,12";
        List<Integer> list2 = Stream.of(str.split(","))
                .map(Integer::valueOf) //将元素映射为Integer
                .filter(x -> !Objects.equals(x, 3))//去除3
                .distinct()//去重
                .sorted(Comparator.reverseOrder())//排序
                .skip(1)  // 跳过前面1个元素
                .limit(4)// 返回开头4个元素，类似于SQL语句中的SELECT TOP
                .collect(Collectors.toList());
        for (int i = 0; i < list2.size(); i++) {
            Integer integer = list2.get(i);
            System.out.println(integer);
        }

//        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
//        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
//        System.out.println(G7Countries);
//
//        Stream.of(1, 8, 5, 2, 1, 0, 9, 2, 0, 4, 8)
//                .filter(n -> n > 2)     // 对元素过滤，保留大于2的元素
//                .distinct()             // 去重，类似于SQL语句中的DISTINCT
//                .skip(1)                // 跳过前面1个元素
//                .limit(2)               // 返回开头2个元素，类似于SQL语句中的SELECT TOP
//                .sorted()               // 对结果排序
//                .forEach(System.out::println);

        List<Book> books = Arrays.asList(
                new Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.00D),
                new Book("Java 8实战", "Mario Fusco", "人民邮电出版社", 79.00D),
                new Book("MongoDB权威指南（第2版）", "Kristina Chodorow", "人民邮电出版社", 69.00D)
        );

// 计算所有图书的总价
        Optional<Double> totalPrice = books.stream()
                .map(Book::getPrice)
                .reduce((n, m) -> n + m);

// 价格最高的图书
        Optional<Book> expensive = books.stream().max(Comparator.comparing(Book::getPrice));
// 价格最低的图书
        Optional<Book> cheapest = books.stream().min(Comparator.comparing(Book::getPrice));
// 计算总数
        long count = books.stream().count();

    }
}
