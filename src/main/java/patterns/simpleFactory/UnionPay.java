package patterns.simpleFactory;

public class UnionPay extends Pay {
    @Override
    public void orderPay() {
        System.out.println("银联支付");
    }
}
