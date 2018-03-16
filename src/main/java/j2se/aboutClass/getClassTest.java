package j2se.aboutClass;

public class getClassTest {
    public static void main(String[] args) {
        Father father = new Father();
        System.out.println(father.getClass());
        System.out.println(Father.class);
        Father father1 = new Son();
        System.out.println(father1.getClass());//输出son
    }
}
