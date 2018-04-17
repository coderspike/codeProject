package spring;

public class SpringDemo {
    /*
    Spring MVC的核心流程是什么样的？

    1、首先用户发送请求——>DispatcherServlet，前端控制器收到请求后自己不进行处理，
    而是委托给其他的解析器进行处理，作为统一访问点，进行全局的流程控制；

    2、DispatcherServlet——>HandlerMapping，HandlerMapping 将会把请求映射为 HandlerExecutionChain 对象
    （包含一个 Handler 处理器（页面控制器）对象、多个 HandlerInterceptor 拦截器）对象，
    通过这种策略模式，很容易添加新的映射策略；

    3、DispatcherServlet——>HandlerAdapter，HandlerAdapter 将会把处理器包装为适配器，从而支持多种类型的处理器，
    即适配器设计模式的应用，从而很容易支持很多类型的处理器；

    4、HandlerAdapter——>处理器功能处理方法的调用，HandlerAdapter 将会根据适配的结果调用真正的处理器的功能处理方法，
    完成功能处理；并返回一个 ModelAndView 对象（包含模型数据、逻辑视图名）；

    5、ModelAndView 的逻辑视图名——> ViewResolver， ViewResolver 将把逻辑视图名解析为具体的 View，
    通过这种策略模式，很容易更换其他视图技术；

    6、View——>渲染，View 会根据传进来的 Model 模型数据进行渲染，此处的 Model 实际是一个 Map 数据结构，因此很容易支持其他视图技术；

    7、返回控制权给 DispatcherServlet，由 DispatcherServlet 返回响应给用户，到此一个流程结束。


    谈谈对Spring IoC的理解。
    IoC不是什么技术，而是一种设计思想，它意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。
    IoC能做什么：把创建和查找依赖对象的控制权交给了容器，由容器进行注入组合对象，所以对象与对象之间是松散耦合，这样也方便测试，利于功能复用，更重要的是使得程序的整个体系结构变得非常灵活；IoC很好的体现了面向对象设计法则之一 – 好莱坞法则；
    IoC和DI是同一个概念的不同角度描述，“依赖注入”明确描述了“被注入对象依赖IoC容器配置依赖对象”。
    在Spring里，BeanFactory提供了IoC容器最基本功能，而 ApplicationContext 则增加了更多支持企业级功能支持

    选择使用Spring框架的原因?
    使用Spring： 第一是使用它的IOC功能，在解耦上达到了配置级别。 第二是使用它对数据库访问事务相关的封装。
    第三就是各种其他组件与Spring的融合，在Spring中更加方便快捷的继承其他一些组件。

     */
}
