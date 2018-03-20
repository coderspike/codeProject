<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-13
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring</title>
</head>
<body>
<%--常用注解--%>
@Component:标准一个普通的spring Bean类。
@Controller:标注一个控制器组件类。
@Service:标注一个业务逻辑组件类。
@Repository:标注一个DAO组件类。
@Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。
@Qualifier：该注解和@Autowired注解搭配使用，用于消除特定bean自动装配的歧义。
@RestController意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了
------------------------------------------------------------------------
<%--基本概念--%>
BeanFactory 接口和 ApplicationContext 接口有什么区别？
ApplicationContext 接口继承BeanFactory接口，Spring核心工厂是BeanFactory ,BeanFactory采取延迟加载
ApplicationContext是对BeanFactory扩展，它可以进行国际化处理、事件传递和bean自动装配以及各种不同应用层的Context实现
开发中基本都在使用ApplicationContext, web项目使用WebApplicationContext ，很少用到BeanFactory
------------------------------------------------------------------------
<%--Bean注入属性有哪几种方式？--%>
<%--spring支持构造器注入和setter方法注入--%>
<%--构造器注入，通过 <constructor-arg> 元素完成注入--%>
<%--setter方法注入， 通过<property> 元素完成注入--%>
------------------------------------------------------------------------
IOC，即“控制反转”，不是什么技术，而是一种设计思想。在Java开发中，IOC意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。
------------------------------------------------------------------------
<%--springMVC工作原理--%>
1.spring mvc请所有的请求都提交给DispatcherServlet,它会委托应用系统的其他模块负责负责对请求进行真正的处理工作。
2.DispatcherServlet查询一个或多个HandlerMapping,找到处理请求的Controller.
3.DispatcherServlet请请求提交到目标Controller
4.Controller进行业务逻辑处理后，会返回一个ModelAndView
5.Dispathcher查询一个或多个ViewResolver视图解析器,找到ModelAndView对象指定的视图对象
6.视图对象负责渲染返回给客户端。
------------------------------------------------------------------------
<%--Sping的容器--%>
1.BeanFactory：是最简答的容器，提供了基本的DI支持。最常用的BeanFactory实现就是XmlBeanFactory类，它根据XML文件中的定义加载beans，该容器从XML文件读取配置元数据并用它去创建一个完全配置的系统或应用。
2.ApplicationContext应用上下文：基于BeanFactory之上构建，并提供面向应用的服务。
------------------------------------------------------------------------
<%--Spring Bean的作用域之间有什么区别？--%>
Spring容器中的bean可以分为5个范围。所有范围的名称都是自说明的,但是为了避免混淆,还是让我们来解释一下：
1、singleton：这种bean范围是默认的，这种范围确保不管接受到多少个请求，每个容器中只有一个bean的实例，
单例的模式由bean factory自身来维护。
2、prototype：原形范围与单例范围相反，为每一个bean请求提供一个实例。
3、request：在请求bean范围内会每一个来自客户端的网络请求创建一个实例,在请求完成以后,bean会失效并被垃圾回收器回收.
4、Session：与请求范围类似，确保每个session中有一个bean的实例，在session过期后，bean会随之失效。
------------------------------------------------------------------------
<%--ApplicationContextAware接口--%>
当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean。换句话说，就是这个类可以直接获取spring配置文件中，所有有引用到的bean对象。
------------------------------------------------------------------------
</body>
</html>
