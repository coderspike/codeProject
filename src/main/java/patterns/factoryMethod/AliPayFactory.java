package patterns.factoryMethod;

/**
 * 支付宝工厂类
 */
public class AliPayFactory extends PayFactory {

    @Override
    public Pay createFactory() {
        return new AliPay();
    }

}