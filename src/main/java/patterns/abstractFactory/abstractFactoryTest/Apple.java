package patterns.abstractFactory.abstractFactoryTest;

public class Apple implements Mobile {
    @Override
    public void getMobile() {
        System.out.println("Apple");
    }

    static {
        System.out.println("Apple");
    }
}
