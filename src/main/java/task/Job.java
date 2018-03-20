package task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job {
	
	//日志记录
	private static final Logger logger = LoggerFactory.getLogger(Job.class);

	public void work(){
		logger.info("fastdfs连接池调度初始化！！！");
	//	FdfsClientPool.init(50);
	}

}
