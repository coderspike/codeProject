package patterns.factoryMethod.factoryMethodTest;

public class FactoryMethodTest {
    public static void main(String[] args) {
        MobileFactory factory = new AppleFactory();
        Mobile mobile = factory.create();
        mobile.getMobile();
    }
}
