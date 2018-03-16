package j2se.aboutClass;

public class Son extends Father {
    public static String staticVar = "子类的静态变量";
    public String var = "子类的变量";

    // 静态初始化块
    static {
        System.out.println(staticVar);
        System.out.println("子类静态初始化块");
    }

    // 初始化块
    {
        System.out.println(var);
        System.out.println("子类初始化块");
    }

    public Son() {
        System.out.println("子类的构造器");
    }

    public static void main(String[] args) {
        new Son();
    }


    /*
实际上的初始化顺序如下:
父类的静态变量
父类静态初始化块
子类的静态变量
子类静态初始化块
父类的变量
父类初始化块
父类的构造器
子类的变量
子类初始化块
子类的构造器
     */
}
