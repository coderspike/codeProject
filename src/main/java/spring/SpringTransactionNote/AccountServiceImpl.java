package spring.SpringTransactionNote;

//@Transactional �����֣�ͨ������ע��������������
/*
������ʽ����ô���֣�����ʱ�����עһЩ����
 */
public class AccountServiceImpl {


	/*
    ��һ�� ���ʽ���� �����ļ����ж�Ӧ���ã��������������������
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
    �ڶ���������� ʹ����XML���õķ�ʽ���������������û��ʹ��AOP
	 */
//	@Override
//	public void transfer( String out, String in, Double money) {
//		accountDao.outMoney(out, money);
//		//int i = 1/0;
//		accountDao.inMoney(in, money);
//
//	}

    /*
    �����֣�����XML aop�����÷�ʽ��Ŀǰ��Ŀ�ж���ʹ�����ַ�ʽ����Ч�ļ����˴�����
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
