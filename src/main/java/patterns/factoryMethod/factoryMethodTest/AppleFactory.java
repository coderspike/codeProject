package patterns.factoryMethod.factoryMethodTest;

public class AppleFactory implements MobileFactory {
    @Override
    public Mobile create() {
        return new Apple();
    }
}
