package patterns.simpleFactory;

public class WechatPay extends Pay {
    @Override
    public void orderPay() {
        System.out.println("微信支付");
    }
}