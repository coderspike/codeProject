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
        User user1 = new User("one", 11, "Сѧ");
        User user2 = new User("two", 11, "Сѧ");
        User user3 = new User("three", 16, "����");
        User user4 = new User("four", 17, "����");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        Map<String, List<User>> userGroupMap = list.stream().collect(Collectors.groupingBy(User::getSchool));
        System.out.println(userGroupMap);

        String str = "1,2,3,4,10,11,9,66,222,12";
        List<Integer> list2 = Stream.of(str.split(","))
                .map(Integer::valueOf) //��Ԫ��ӳ��ΪInteger
                .filter(x -> !Objects.equals(x, 3))//ȥ��3
                .distinct()//ȥ��
                .sorted(Comparator.reverseOrder())//����
                .skip(1)  // ����ǰ��1��Ԫ��
                .limit(4)// ���ؿ�ͷ4��Ԫ�أ�������SQL����е�SELECT TOP
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
//                .filter(n -> n > 2)     // ��Ԫ�ع��ˣ���������2��Ԫ��
//                .distinct()             // ȥ�أ�������SQL����е�DISTINCT
//                .skip(1)                // ����ǰ��1��Ԫ��
//                .limit(2)               // ���ؿ�ͷ2��Ԫ�أ�������SQL����е�SELECT TOP
//                .sorted()               // �Խ������
//                .forEach(System.out::println);

        List<Book> books = Arrays.asList(
                new Book("Java���˼��", "Bruce Eckel", "��е��ҵ������", 108.00D),
                new Book("Java 8ʵս", "Mario Fusco", "�����ʵ������", 79.00D),
                new Book("MongoDBȨ��ָ�ϣ���2�棩", "Kristina Chodorow", "�����ʵ������", 69.00D)
        );

// ��������ͼ����ܼ�
        Optional<Double> totalPrice = books.stream()
                .map(Book::getPrice)
                .reduce((n, m) -> n + m);

// �۸���ߵ�ͼ��
        Optional<Book> expensive = books.stream().max(Comparator.comparing(Book::getPrice));
// �۸���͵�ͼ��
        Optional<Book> cheapest = books.stream().min(Comparator.comparing(Book::getPrice));
// ��������
        long count = books.stream().count();

    }
}
