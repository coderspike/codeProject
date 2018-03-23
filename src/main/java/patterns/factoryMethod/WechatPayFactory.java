package patterns.factoryMethod;

public class WechatPayFactory extends PayFactory {

    @Override
    public Pay createFactory() {
        return new WechatPay();
    }

}