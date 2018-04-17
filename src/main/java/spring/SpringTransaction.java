package spring;

/**
 * Spring 事物介绍
 */
public class SpringTransaction {

    /*
    面试的常见题
    spring 管理事务的方式有两种
    1 编程式
    2 声明式

    Spring的事务管理是通过事务管理器来实现的
    一般我们比较常用的就是HibernateTransactionManager和DataSourceTransactionManager。
    现在用mybatis一般用DataSourceTransactionManager
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

    事物的5个属性：
    1、传播行为
    Service接口方法可能会在内部调用其它的Service接口方法以共同完成一个完整的业务操作，
    因此就会产生服务接口方法嵌套调用的情况，Spring通过事务传播行为控制当前的事务如何传播到被嵌套调用的目标服务接口方法中。
    新的事务应该被启动还是挂起，或者方法是否要在事务环境中运行
    Spring框架在TransactionDefinition接口中固定了7种事务传播行为
    PROPAGATION_REQUIRED 如果当前没有事务,就新建一个事务,如果已经存在一个事务中,加入到这个事务中.
    PROPAGATION_SUPPORTS 如果当前存在一个事务,则加入这个事务,如果当前没有事务,就以非事务方式执行
    PROPAGATION_MANDATOR 使用当前的事务,如果当前没有事务,就抛出异常
    PROPAGATION_REQUIRES_NEW 新建事务,如果当前存在事务,把当前事务挂起
    PROPAGATION_NOT_SUPPORTED 以非事务方式执行操作,如果当前存在事务,就把当前事务挂起
    PROPAGATION_NEVER 以非事务方式执行,如果当前存在事务,则抛出异常
    PROPAGATION_NESTED 如果当前存在事务,则在嵌套事务内执行.如果当前没有事务,则执行与PROPAGATION_REQUIRED类似的操作


    2、隔离级别 (强度由弱到强)
    隔离级别定义了一个事务可能受其他并发事务影响的程度。多事务并发可能会导致脏读、幻读、不可重复读等各种读现象。
    ISOLATION_DEFAULT:使用后端数据库默认的规则，对大部分数据库而言,通常这值就是TransactionDefinition.ISOLATION_READ_COMMITTED.

    ISOLATION_READ_UNCOMMITTED:该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据.该级别没有用到锁,
    多个事务可以并发修改同一行数据,因此不能防止脏读和不可重复读.

    ISOLATION_READ_COMMITTED:该隔离级别表示一个事务只能读取另一个事务已经提交的数据.该级别用到了行锁,
    查询的时候读取的是快照的数据,每次查询读取的是最新的快照,
    ,可以防止脏读,但是存在幻读取,因为行锁只能锁住修改,不能锁住插入和删除。

    ISOLATION_REPEATABLE_READ:该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询,并且每次返回的记录都相同,
    同个事务内多次查询读取到的是同一个快照(第一次查询时的快照),因此可以防止脏读和不可重复读,

    ISOLATION_SERIALIZABLE:所有的事务依次顺序执行,该级别可以防止脏读、不可重复读以及幻读,通常情况下不会用到该级别.

    3、是否只读
    4、事务超时
    所谓事务超时,就是指一个事务的最长执行时间,如果超过该时间限制但事务还没有完成,则自动回滚事务.
    在TransactionDefinition中以int的值来表示超时时间,其单位是秒.
    5、事物回滚
    Spring的默认回滚规则是,如果在事务中抛出了非检查异常(继承自RuntimeException 的异常),则默认将回滚事务.
    如果没有抛出任何异常,或者抛出了已检查异常,则仍然提交事务

    配置方式：
    isolation	指定事务的隔离级别
    propagation	定义事务的传播规则
    read-only	指定事务为只读
    rollback-for指定事务对哪些检查型异常应当回滚而不提交
    no-rollback-for指定事务对哪些异常应当继续执行而不回滚
    timeout	对于长时间运行的事务定义超时时间

    XML中事务属性的配置方式如下：
    <tx:advice id="txAdvice" transactionmanager="transactionManager">
            <tx:attributes>
                <tx:method name="*" propagation="REQUIRED" read-only="true"/>
            </tx:attributes>
    </tx:advice>

    注解中事务属性的配置方式如下：
    @Transaction(propagation=Propagation. REQUIRED,readOnly=true)
    public void add(String username){
        //...
    }
     */

    /*
    Spring事务抽象中一种关键的概念是：事务策略。
    一个事务策略，由PlatformTransactionManager接口所定义
    public interface PlatformTransactionManager {
      TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
      void commit(TransactionStatus status) throws TransactionException;
      void rollback(TransactionStatus status) throws TransactionException;
     }
     PlatformTransactionManager是事务管理的抽象层，Spring根据这个抽象层提供许多不同的具体实现。
     无论是声明式还是编程式的进行事务管理，你都必须正确的定义PlatformTransactionManager的实现。

     Spring事务管理SPI主要包括3个接口：PlatformTransactionManager（进行事务的创建、提交或回滚）、TransactionDefinition（定义事务属性，如隔离级别）和TransactionStatus（事务运行时状态，如是否已完成）。这三者通过PlatformTransactionManager的如下接口进行关联：
        // 根据事务定义创建事务，并由TransactionStatus表示它
        TransactionStatus getTransaction(TransactionDefinition definition);
        // 根据事务运行时状态提交或回滚事务
        void commit(TransactionStatus status);
        void rollback(TransactionStatus status);

     示例：
     <!-- 定义事务通知 （事务的增强）-->
        <tx:advice id="txAdvice" transaction-manager="transactionManager">
            <!-- 定义方法的过滤规则 -->
            <tx:attributes>
                <!-- 所有方法都使用事务 -->
                <!--
                    propagation:事务传播行为
                    isolation：事务隔离
                    read-only:只读
                    rollback-for:发生哪些异常回滚
                    no-rollback-for:发生哪些异常不回滚
                    timeout:过期信息
                 -->
                <tx:method name="create*" propagation="REQUIRED"/>
                <tx:method name="add*" propagation="REQUIRED"/>
                <tx:method name="delete*" propagation="REQUIRED"/>
                <tx:method name="update*" propagation="REQUIRED"/>
                <tx:method name="change*" propagation="REQUIRED"/>
            </tx:attributes>
        </tx:advice>

        <!-- 定义AOP配置 配置切面 -->
        <aop:config>
            <!-- 定义一个切入点 -->
            <aop:pointcut expression="execution (* ymy.com.service.impl.*.*(..))" id="services"/>
            <!-- 对切入点和事务的通知，进行适配 -->
            <aop:advisor advice-ref="txAdvice" pointcut-ref="services"/>
        </aop:config>
     */
    /*
    脏读：
    一个事务读取了另一个事务改写但还未提交的数据，如果这些数据被回滚，则读到的数据是无效的。
    不可重复读：
    在同一事物中，多次读取同一数据返回的结果有所不同。
    幻读（虚读）：
    一个事务读取了几行记录后，另一个事务插入一些记录，幻读就发生了。在后来的查询中，第一个事务就会发现原来没有的记录。
     */

}
