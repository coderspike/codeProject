package patterns.simpleFactory;

/**
 * 简单工厂模式：根据参数的不同，返回不同的类型
 */
public class PayFactory {
    public static Pay choicePay(String payMethod) {
        Pay pay = null;
        if ("aliPay".equals(payMethod)) {
            pay = new AliPay();
        } else if ("wechatPay".equals(payMethod)) {
            pay = new WechatPay();
        } else if ("unionPay".equals(payMethod)) {
            pay = new UnionPay();
        }

        return pay;
    }

    public static void main(String[] args) {
        String payMethod = "aliPay";
        Pay pay = PayFactory.choicePay(payMethod);
        if (pay != null) {
            pay.init();
            pay.orderPay();
        } else {
            System.out.println("未选择支付方式");
        }

    }
}