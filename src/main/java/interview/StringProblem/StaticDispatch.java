package interview.StringProblem;

public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayhello(Human guy) {
        System.out.println("Human guy");

    }

    public void sayhello(Man guy) {
        System.out.println("Man guy");

    }

    public void sayhello(Woman guy) {
        System.out.println("Woman guy");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayhello(man);// Human guy
        staticDispatch.sayhello(woman);// Human guy
    }
}

/**
 * 运行结果：
 * <p>
 * Human guy
 * <p>
 * Human guy
 * <p>
 * 为什么会出现这样的结果呢？
 * <p>
 * Human man = new Man();其中的Human称为变量的静态类型（Static Type）,Man称为变量的实际类型（Actual Type）。
 * 两者的区别是：静态类型在编译器可知，而实际类型到运行期才确定下来。
 * 在重载时通过参数的静态类型而不是实际类型作为判定依据，因此，在编译阶段，
 * Javac编译器会根据参数的静态类型决定使用哪个重载版本。所以选择了sayhello(Human)作为调用目标，
 * 并把这个方法的符号引用写到main()方法里的两条invokevirtual指令的参数中。
 */