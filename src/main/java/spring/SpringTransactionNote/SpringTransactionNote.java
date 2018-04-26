package spring.SpringTransactionNote;

public class SpringTransactionNote {
    /*
    Spring事务管理器的接口是org.springframework.transaction.PlatformTransactionManager【很重要】

    public interface PlatformTransactionManager {
        //根据TransactionDefinition得到TransactionStatus
        //通过TransactionDefinition 来获取事物的状态，其中TransactionDefinition 就保存了事物的基本属性。
        TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
        //根据TransactionDefinition得到TransactionStatus
        void commit(TransactionStatus status) throws TransactionException;
        //根据TransactionDefinition得到TransactionStatus
        void rollback(TransactionStatus status) throws TransactionException;
    }

    public interface TransactionDefinition {
        int PROPAGATION_REQUIRED = 0;
        int PROPAGATION_SUPPORTS = 1;
        int PROPAGATION_MANDATORY = 2;
        int PROPAGATION_REQUIRES_NEW = 3;
        int PROPAGATION_NOT_SUPPORTED = 4;
        int PROPAGATION_NEVER = 5;
        int PROPAGATION_NESTED = 6;
        int ISOLATION_DEFAULT = -1;

        int ISOLATION_READ_UNCOMMITTED = Connection.TRANSACTION_READ_UNCOMMITTED;
        int ISOLATION_READ_COMMITTED = Connection.TRANSACTION_READ_COMMITTED;
        int ISOLATION_REPEATABLE_READ = Connection.TRANSACTION_REPEATABLE_READ;
        int ISOLATION_SERIALIZABLE = Connection.TRANSACTION_SERIALIZABLE;

        int TIMEOUT_DEFAULT = -1;
        //获取事务的传播行为
        int getPropagationBehavior();
        //获取事务的隔离级别
        int getIsolationLevel();
        //获取事务的超时时间
        int getTimeout();
        //获取事务是否只读
        boolean isReadOnly();
        //获取事务的传播行为
        String getName();

    事务的传播行为
    事务的传播行为是指当事务方法被另一个事务方法调用时，必须指定事务应该如何传播。
    例如：方法可能继续在现有事务中运行，也可能开启一个新事务，并在自己的事务中运行。Spring定义了七种传播行为：

    （1）PROPAGATION_REQUIRED：
    表示当前方法必须运行在事务中。如果当前事务存在，方法将会在该事务中运行。否则，会启动一个新的事务
    注：以下具体讲解传播行为的内容参考自Spring事务机制详解

    //事务属性 PROPAGATION_REQUIRED
    methodA{
        ……
        methodB();
        ……
    }

    //事务属性 PROPAGATION_REQUIRED
    methodB{
       ……
    }
    单独调用methodB方法：

    main{
        metodB();
    }
    相当于

    Main{
        Connection con=null;
        try{
            con = getConnection();
            con.setAutoCommit(false);

            //方法调用
            methodB();

            //提交事务
            con.commit();
        } Catch(RuntimeException ex) {
            //回滚事务
            con.rollback();
        } finally {
            //释放资源
            closeCon();
        }
    }
    Spring保证在methodB方法中所有的调用都获得到一个相同的连接。在调用methodB时，没有一个存在的事务，所以获得一个新的连接，开启了一个新的事务。
    单独调用MethodA时，在MethodA内又会调用MethodB.

    执行效果相当于：

    main{
        Connection con = null;
        try{
            con = getConnection();
            methodA();
            con.commit();
        } catch(RuntimeException ex) {
            con.rollback();
        } finally {
            closeCon();
        }
    }
    调用MethodA时，环境中没有事务，所以开启一个新的事务.当在MethodA中调用MethodB时，环境中已经有了一个事务，所以methodB就加入当前事务。


（2）PROPAGATION_SUPPORTS
    如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行。但是对于事务同步的事务管理器，PROPAGATION_SUPPORTS与不使用事务有少许不同。

    //事务属性 PROPAGATION_REQUIRED
    methodA(){
      methodB();
    }

    //事务属性 PROPAGATION_SUPPORTS
    methodB(){
      ……
    }
    单纯的调用methodB时，methodB方法是非事务的执行的。当调用methdA时,methodB则加入了methodA的事务中,事务地执行。

    （3）PROPAGATION_MANDATORY
    如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。

    //事务属性 PROPAGATION_REQUIRED
    methodA(){
        methodB();
    }

    //事务属性 PROPAGATION_MANDATORY（必须的）
        methodB(){
        ……
    }
    当单独调用methodB时，因为当前没有一个活动的事务，
    则会抛出异常throw new IllegalTransactionStateException(“Transaction propagation ‘mandatory’ but no existing transaction found”);
    当调用methodA时，methodB则加入到methodA的事务中，事务地执行。

    （4）PROPAGATION_REQUIRES_NEW
    总是开启一个新的事务。如果一个事务已经存在，则将这个存在的事务挂起。

    //事务属性 PROPAGATION_REQUIRED
    methodA(){
        doSomeThingA();
        methodB();
        doSomeThingB();
    }

    //事务属性 PROPAGATION_REQUIRES_NEW
    methodB(){
        ……
    }
    调用A方法：

    main(){
        methodA();
    }
    相当于

    main(){
        TransactionManager tm = null;
        try{
            //获得一个JTA事务管理器
            tm = getTransactionManager();
            tm.begin();//开启一个新的事务
            Transaction ts1 = tm.getTransaction();
            doSomeThing();
            tm.suspend();//挂起当前事务
            try{
                tm.begin();//重新开启第二个事务
                Transaction ts2 = tm.getTransaction();
                methodB();
                ts2.commit();//提交第二个事务
            } Catch(RunTimeException ex) {
                ts2.rollback();//回滚第二个事务
            } finally {
                //释放资源
            }
            //methodB执行完后，恢复第一个事务
            tm.resume(ts1);
            doSomeThingB();
            ts1.commit();//提交第一个事务
        } catch(RunTimeException ex) {
            ts1.rollback();//回滚第一个事务
        } finally {
            //释放资源
        }
    }
    在这里，我把ts1称为外层事务，ts2称为内层事务。从上面的代码可以看出，ts2与ts1是两个独立的事务，互不相干。Ts2是否成功并不依赖于 ts1。如果methodA方法在调用methodB方法后的doSomeThingB方法失败了，而methodB方法所做的结果依然被提交。而除了 methodB之外的其它代码导致的结果却被回滚了。使用PROPAGATION_REQUIRES_NEW,需要使用 JtaTransactionManager作为事务管理器。

    （5）PROPAGATION_NOT_SUPPORTED
    总是非事务地执行，并挂起任何存在的事务。使用PROPAGATION_NOT_SUPPORTED,也需要使用JtaTransactionManager作为事务管理器。（代码示例同上，可同理推出）

    （6）PROPAGATION_NEVER
    总是非事务地执行，如果存在一个活动事务，则抛出异常。

    （7）PROPAGATION_NESTED
    如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。这是一个嵌套事务,使用JDBC 3.0驱动时,仅仅支持DataSourceTransactionManager作为事务管理器。需要JDBC 驱动的java.sql.Savepoint类。有一些JTA的事务管理器实现可能也提供了同样的功能。使用PROPAGATION_NESTED，还需要把PlatformTransactionManager的nestedTransactionAllowed属性设为true;而 nestedTransactionAllowed属性值默认为false。

    //事务属性 PROPAGATION_REQUIRED
    methodA(){
        doSomeThingA();
        methodB();
        doSomeThingB();
    }

    //事务属性 PROPAGATION_NESTED
    methodB(){
        ……
    }
    如果单独调用methodB方法，则按REQUIRED属性执行。如果调用methodA方法，相当于下面的效果：

    main(){
        Connection con = null;
        Savepoint savepoint = null;
        try{
            con = getConnection();
            con.setAutoCommit(false);
            doSomeThingA();
            savepoint = con2.setSavepoint();
            try{
                methodB();
            } catch(RuntimeException ex) {
                con.rollback(savepoint);
            } finally {
                //释放资源
            }
            doSomeThingB();
            con.commit();
        } catch(RuntimeException ex) {
            con.rollback();
        } finally {
            //释放资源
        }
    }
    当methodB方法调用之前，调用setSavepoint方法，保存当前的状态到savepoint。如果methodB方法调用失败，则恢复到之前保存的状态。但是需要注意的是，这时的事务并没有进行提交，如果后续的代码(doSomeThingB()方法)调用失败，则回滚包括methodB方法的所有操作。

    嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。
     */


}
