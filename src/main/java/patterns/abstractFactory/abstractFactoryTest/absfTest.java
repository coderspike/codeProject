package patterns.abstractFactory.abstractFactoryTest;

public class absfTest {
    public static void main(String[] args) {
        ElectronicFactory installFactoryA = new InstallFactoryA();
        installFactoryA.createHeadPhone();
        installFactoryA.createMobile();

        ElectronicFactory installFactoryB = new InstallFactoryB();
        installFactoryB.createHeadPhone();
        installFactoryB.createMobile();
    }
}
