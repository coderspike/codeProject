package util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * 初始化实例Bean工具类
 * ApplicationContext是Spring的核心，Context通常解释为上下文环境
 */
public class SpringBeanUtil {

    private static WebApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = (WebApplicationContext) applicationContext;
    }

    /**
     * 获取bean
     *
     * @param beanName
     * @return
     */
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

}
