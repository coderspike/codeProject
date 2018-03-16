package j2se.aboutClass;

public class Father implements Cloneable {
    public static String staticVar = "父类的静态变量";
    public String var = "父类的变量";

    // 静态初始化块
    static {
        System.out.println(staticVar);
        System.out.println("父类静态初始化块");
    }

    // 初始化块
    {
        System.out.println(var);
        System.out.println("父类初始化块");
    }

    public Father() {
        System.out.println("父类的构造器");
    }

    public static void main(String[] args) {
        new Father();
    }
}
