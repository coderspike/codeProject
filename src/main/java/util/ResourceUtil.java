package util;

import java.util.ResourceBundle;

public class ResourceUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    /**
     * 通过key获取配置文件中的value值，如果不存在该key则返回null
     */
    public static String getPropertiesValue(String key) {
        return bundle.containsKey(key) ? bundle.getString(key) : null;
    }

    /**
     * 通过key获取配置文件中的value值，如果不存在该key则返回null
     */
    public static Integer getPropertiesIntegerValue(String key) {
        return bundle.containsKey(key) ? Integer.valueOf(bundle.getString(key)) : null;
    }
}
