package patterns.Template;

/**
 * 模板方法模式：客户端测试类
 */
public class TestTemplateMethod {
    public static void main(String[] args) {
        AbstractTemplate abstractTemplate = new ConcreteTemplateA();
        abstractTemplate.templateMethod();
        abstractTemplate = new ConcreteTemplateB();
        abstractTemplate.templateMethod();
    }
}
