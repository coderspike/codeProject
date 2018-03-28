package patterns.Adapter;

/**
 * 适配器类
 */
public class Adapter implements ITarget {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        System.out.println("Adapter.request");
        adaptee.onRequest();
    }
}