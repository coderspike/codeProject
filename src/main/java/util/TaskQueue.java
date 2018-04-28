/**  
 * @Description:  
 * @author:dupengfei  
 * @date 2018年4月27日  
 * @version V1.0  
 */ 
package util;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author DAGONG
 *
 */
public class TaskQueue {
    private static  LinkedBlockingQueue queues = null;  
    
    public static LinkedBlockingQueue getTaskQueue(){  
        if(queues==null){  
            queues =  new LinkedBlockingQueue();  
            System.out.println("初始化 队列");  
        }  
        return queues;  
    }  
      
    public static void add(Object obj){  
        if(queues==null)  
            queues =  getTaskQueue();  
        queues.offer(obj);  
        System.out.println("-------------------------------");  
        System.out.println("入队："+obj);  
    }
}
