package patterns.Adapter;

/**
 * 目标接口实现类
 */
public class ConcreteTarget implements ITarget {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request()");
    }
}
