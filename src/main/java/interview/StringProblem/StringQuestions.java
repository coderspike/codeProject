package interview.StringProblem;

public class StringQuestions {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = s1;
        String s3 = new String("abc");
        String s4 = new String("abc");
        String s5 = "abc";

        System.out.println(s1 == s5);//"true"
        System.out.println(s1 == s2);//"true"
        System.out.println(s1.equals(s2));//"true"
        System.out.println(s3 == s4);//"false"
        System.out.println(s1.equals(s4));//"true"
        System.out.println(s3.equals(s4));//"true"
    }
}
