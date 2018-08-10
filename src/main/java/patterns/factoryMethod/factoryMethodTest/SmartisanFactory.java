package patterns.factoryMethod.factoryMethodTest;

public class SmartisanFactory implements MobileFactory {
    @Override
    public Mobile create() {
        return new Smartisan();
    }
}
