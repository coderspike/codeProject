package patterns.factoryMethod;

/**
 * 相比简单工厂模式又向上抽象了一层
 * 简单工厂 工厂方法 抽象工厂
 */
public abstract class Pay {
    public void init() {
        System.out.println("支付方式初始化");
    }

    public abstract void orderPay();
}
