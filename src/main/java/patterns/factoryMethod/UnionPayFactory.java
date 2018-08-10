package patterns.factoryMethod;

public class UnionPayFactory extends PayFactory {

    @Override
    public Pay createFactory() {
        return new UnionPay();
    }

}