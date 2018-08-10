package patterns.Decorator;

// 装饰者
public abstract class GirlDecorator extends Girl {

    public abstract String getDescription();

}

// 下面以美国女孩示例
// 给美国女孩加上金发
class GoldenHair extends GirlDecorator {
    private Girl girl;

    public GoldenHair(Girl g) {
        girl = g;
    }

    @Override
    public String getDescription() {
        return girl.getDescription() + "+with golden hair";
    }

}

// 加上身材高大的特性
class Tall extends GirlDecorator {
    private Girl girl;

    public Tall(Girl g) {
        girl = g;
    }

    @Override
    public String getDescription() {
        return girl.getDescription() + "+is very tall";
    }

}
