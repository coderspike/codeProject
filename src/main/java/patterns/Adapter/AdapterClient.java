package patterns.Adapter;

public class AdapterClient {
    public static void main(String[] args) {
        ITarget adapter = new Adapter();
        adapter.request();
        System.out.println("------------------");
        ITarget target = new ConcreteTarget();
        target.request();
    }
}

/**
 * 调用双方接口不一致且都不容易修改时，可以使用适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * 多个组件功能类似，但接口不统一且可能会经常切换时，可使用适配器模式，使得客户端可以以统一的接口使用它们
 */