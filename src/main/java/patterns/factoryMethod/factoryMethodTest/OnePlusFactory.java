package patterns.factoryMethod.factoryMethodTest;

public class OnePlusFactory implements MobileFactory {

    @Override
    public Mobile create() {
        return new OnePlus();
    }
}
