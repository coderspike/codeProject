package interview.StringProblem;

public class DynamicDisptch {

    static abstract class Human {
        abstract void sayhello();
    }

    static class Man extends Human {

        @Override
        void sayhello() {
            System.out.println("man");
        }

    }

    static class Woman extends Human {

        @Override
        void sayhello() {
            System.out.println("woman");
        }

    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayhello();
        woman.sayhello();
        man = new Woman();
        man.sayhello();
    }

}
/*
运行结果：
man
woman
woman
 */
