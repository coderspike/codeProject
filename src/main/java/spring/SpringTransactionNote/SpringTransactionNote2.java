package spring.SpringTransactionNote;

public class SpringTransactionNote2 {
    /*
隔离级别
事务的第二个维度就是隔离级别（isolation level）。隔离级别定义了一个事务可能受其他并发事务影响的程度。
（1）并发事务引起的问题
在典型的应用程序中，多个事务并发运行，经常会操作相同的数据来完成各自的任务。并发虽然是必须的，但可能会导致一下的问题。

脏读（Dirty reads）——脏读
发生在一个事务读取了另一个事务改写但尚未提交的数据时。如果改写在稍后被回滚了，那么第一个事务获取的数据就是无效的。

不可重复读（Nonrepeatable read）——不可重复读
发生在一个事务执行相同的查询两次或两次以上，但是每次都得到不同的数据时。这通常是因为另一个并发事务在两次查询期间进行了更新。

幻读（Phantom read）——幻读
与不可重复读类似。它发生在一个事务（T1）读取了几行数据，接着另一个并发事务（T2）插入了一些数据时。在随后的查询中，第一个事务（T1）就会发现多了一些原本不存在的记录。
不可重复读与幻读的区别

不可重复读的重点是修改:
同样的条件, 你读取过的数据, 再次读取出来发现值不一样了
例如：在事务1中，Mary 读取了自己的工资为1000,操作并没有完成

 con1 = getConnection();
    select salary from employee empId ="Mary";
在事务2中，这时财务人员修改了Mary的工资为2000,并提交了事务.

 con2 = getConnection();
    update employee set salary = 2000;
    con2.commit();
在事务1中，Mary 再次读取自己的工资时，工资变为了2000

   //con1
    select salary from employee empId ="Mary";
在一个事务中前后两次读取的结果并不一致，导致了不可重复读。

幻读的重点在于新增或者删除：
同样的条件, 第1次和第2次读出来的记录数不一样
例如：目前工资为1000的员工有10人。事务1,读取所有工资为1000的员工。

 con1 = getConnection();
    Select * from employee where salary =1000;
共读取10条记录

这时另一个事务向employee表插入了一条员工记录，工资也为1000

  con2 = getConnection();
    Insert into employee(empId,salary) values("Lili",1000);
    con2.commit();
事务1再次读取所有工资为1000的员工

   //con1
    select * from employee where salary =1000;
共读取到了11条记录，这就产生了幻像读。

从总的结果来看, 似乎不可重复读和幻读都表现为两次读取的结果不一致。但如果你从控制的角度来看, 两者的区别就比较大。
对于前者, 只需要锁住满足条件的记录。
对于后者, 要锁住满足条件及其相近的记录。

几种隔离级别

ISOLATION_DEFAULT
使用后端数据库默认的隔离级别

ISOLATION_READ_UNCOMMITTED
最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读

ISOLATION_READ_COMMITTED
允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生

ISOLATION_REPEATABLE_READ
对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生

ISOLATION_SERIALIZABLE
最高的隔离级别，完全服从ACID的隔离级别，确保阻止脏读、不可重复读以及幻读，也是最慢的事务隔离级别，因为它通常是通过完全锁定事务相关的数据库表来实现的

只读
事务的第三个特性是它是否为只读事务。如果事务只对后端的数据库进行该操作，数据库可以利用事务的只读特性来进行一些特定的优化。通过将事务设置为只读，你就可以给数据库一个机会，让它应用它认为合适的优化措施。

事务超时
为了使应用程序很好地运行，事务不能运行太长的时间。因为事务可能涉及对后端数据库的锁定，所以长时间的事务会不必要的占用数据库资源。事务超时就是事务的一个定时器，在特定时间内事务如果没有执行完毕，那么就会自动回滚，而不是一直等待其结束。

回滚规则
事务五边形的最后一个方面是一组规则，这些规则定义了哪些异常会导致事务回滚而哪些不会。默认情况下，事务只有遇到运行期异常时才会回滚，而在遇到检查型异常时不会回滚（这一行为与EJB的回滚行为是一致的）
但是你可以声明事务在遇到特定的检查型异常时像遇到运行期异常那样回滚。同样，你还可以声明事务遇到特定的异常不回滚，即使这些异常是运行期异常。

附带小例子一个

    /**
     * 1.添加事务注解
     * 使用propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时如何使用事务。
     * 默认取值为REQUIRED，即使用调用方法的事务
     * REQUIRES_NEW：使用自己的事务，调用的事务方法的事务被挂起。
     *
     * 2.使用isolation 指定事务的隔离级别，最常用的取值为READ_COMMITTED
     * 3.默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置。通常情况下，默认值即可。
     * 4.使用readOnly 指定事务是否为只读。 表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务。若真的是一个只读取数据库值得方法，应设置readOnly=true
     * 5.使用timeOut 指定强制回滚之前事务可以占用的时间。
     */
//    @Transactional(propagation= Propagation.REQUIRES_NEW,
//            isolation= Isolation.READ_COMMITTED,
//            noRollbackFor={UserAccountException.class},
//            readOnly=true, timeout=3)
//    @Override
//    public void purchase(String username, String isbn) {
//        //doSomething.....
//    }
}
