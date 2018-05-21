package patterns.abstractFactory.abstractFactoryTest;

public class InstallFactoryB implements ElectronicFactory {
    @Override
    public Headphone createHeadPhone() {
        return new AKG();
    }

    @Override
    public Mobile createMobile() {
        return new OnePlus();
    }
}
