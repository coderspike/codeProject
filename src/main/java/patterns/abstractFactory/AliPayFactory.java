package patterns.abstractFactory;

public class AliPayFactory extends PayFactory {

    @Override
    public MorningPay getMorningPay() {
        return new MorningAliPay();
    }

    @Override
    public AfternoonPay getAfternoonPay() {
        return new AfternoonAliPay();
    }

}
