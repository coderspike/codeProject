package patterns.abstractFactory;

/**
 * 早晨支付宝支付类
 */
public class MorningAliPay extends MorningPay {

    @Override
    public void morningOrderPay() {
        System.out.println("支付宝支付");
    }

}
