package patterns.Strategy;

/**
 * 策略模式：具体算法A
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("算法A实现");
    }
}