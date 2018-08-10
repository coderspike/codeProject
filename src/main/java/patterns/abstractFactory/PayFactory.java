package patterns.abstractFactory;

public abstract class PayFactory {
    public abstract MorningPay getMorningPay();

    public abstract AfternoonPay getAfternoonPay();
}