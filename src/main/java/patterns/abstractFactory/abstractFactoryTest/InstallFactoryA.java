package patterns.abstractFactory.abstractFactoryTest;

public class InstallFactoryA implements ElectronicFactory {
    @Override
    public Headphone createHeadPhone() {
        return new Beats();
    }

    @Override
    public Mobile createMobile() {
        return new Apple();
    }
}
