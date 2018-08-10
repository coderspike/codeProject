package patterns.factoryMethod;

public class AliPay extends Pay {

    @Override
    public void orderPay() {
        System.out.println("支付宝支付");
    }

}