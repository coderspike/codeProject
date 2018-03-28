package patterns.Strategy;

/**
 * 策略模式：抽象策略类
 * 策略模式（strategy pattern）：属于对象的行为模式。
 * 策略模式定义了一系列的算法，并将每个算法封装起来，让他们之间可以相互转换，该模式让算法的变化，不会影响到使用算法的客户。
 */
public interface Strategy {
    /**
     * 算法方法
     */
    public void algorithmInterface();
}