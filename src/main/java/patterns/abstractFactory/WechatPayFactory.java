package patterns.abstractFactory;

public class WechatPayFactory extends PayFactory {

    @Override
    public MorningPay getMorningPay() {
        return new MorningWechatPay();
    }

    @Override
    public AfternoonPay getAfternoonPay() {
        return new AfternoonWechatPay();
    }
}