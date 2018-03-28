package patterns.Strategy;

/**
 * 策略模式：具体算法B
 */
public class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("算法B实现");
    }
}