package patterns.Strategy;

/**
 * 策略模式：具体算法C
 */
public class ConcreteStrategyC implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("算法C实现");
    }
}
