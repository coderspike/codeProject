package patterns.Template;

/**
 * 模板方法模式：具体模板类B
 */
public class ConcreteTemplateB extends AbstractTemplate {
    @Override
    protected void primitiveOperation1() {
        System.out.println("具体模板类B实现方法1");
    }

    @Override
    protected void primitiveOperation2() {
        System.out.println("具体模板类B实现方法2");
    }
}
