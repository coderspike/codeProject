package patterns.abstractFactory;

/**
 * 早晨微信支付类
 */
public class MorningWechatPay extends MorningPay {

    @Override
    public void morningOrderPay() {
        System.out.println("微信支付");
    }

}