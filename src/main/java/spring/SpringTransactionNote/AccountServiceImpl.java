package spring.SpringTransactionNote;

//@Transactional 第四种，通过开启注解来完成事物管理
/*
基本方式就这么几种，面试时候更关注一些概念
 */
public class AccountServiceImpl {


	/*
    第一种 编程式事务 配置文件中有对应配置，很少用这这事物管理了
	 */
//	public void transfer(final String out, final String in, final Double money) {
//		/*accountDao.outMoney(out, money);
//		//int i = 1/0;
//		accountDao.inMoney(in, money);*/
//
//		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				accountDao.outMoney(out, money);
//				//int i = 1/0;
//				accountDao.inMoney(in, money);
//			}
//		});
//	}

	/*
    第二种事务管理 使用了XML配置的方式进行事物管理，但是没有使用AOP
	 */
//	@Override
//	public void transfer( String out, String in, Double money) {
//		accountDao.outMoney(out, money);
//		//int i = 1/0;
//		accountDao.inMoney(in, money);
//
//	}

    /*
    第三种，基于XML aop的配置方式，目前项目中都是使用这种方式，有效的减少了代码量
     */
//    @Override
//    public void transfer( String out, String in, Double money) {
//        accountDao.outMoney(out, money);
//        //int i = 1/0;
//        accountDao.inMoney(in, money);
//
//    }
//
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }


}
