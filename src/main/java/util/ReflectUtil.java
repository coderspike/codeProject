package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {
    /**
     * 获取实体类set方法
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Method getSetMethod(Class clazz, String fieldName) {
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Field field = clazz.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            method = clazz.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    /**
     * 获取实体类get方法
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Method getGetMethod(Class clazz, String fieldName) {
        Method method = null;
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            method = clazz.getMethod(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
}
