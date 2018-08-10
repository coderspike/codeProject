package patterns.simpleFactory;


public abstract class Pay {
    public void init() {
        System.out.println("支付方式初始化");
    }

    public abstract void orderPay();
}