package util;

import base.DictionaryDetail;
import cache.EhcacheManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***********************************
 * 2018-02-05
 * @author:GWB
 * 通用changeModelUtil
 * 自己封装的工具类，主要用于model和PageModel的转化 pageModel中要转化的属性字段以str结尾
 *
 ***********************************
 */
public class ChangeModelUtil {
    /**
     * 通用changeModel
     *
     * @param listBO
     * @param clazzName
     * @param dictionaryMap
     * @return
     */
    public static List<?> changeModel(List<?> listBO, String clazzName, Map dictionaryMap) {
        List<Object> listVO = new ArrayList<Object>();
        if (listBO != null && listBO.size() != 0) {
            for (int i = 0; i < listBO.size(); i++) {
                Object voObject = null;
                try {
                    Class clazz = Class.forName(clazzName);
                    voObject = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                BeanUtils.copyProperties(listBO.get(i), voObject);
                if (dictionaryMap.keySet().size() > 0) {
                    for (Iterator it = dictionaryMap.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        String valueStr = value.toString() + "Str";//要转化的属性默认Str结尾
                        //Ehcache为项目级的缓存，项目重启就消失了，像数据字典这类的缓存可以加在Ehcache中
                        List<DictionaryDetail> dictionaryDetailList = (List<DictionaryDetail>) EhcacheManager.get(key.toString());
                        try {
                            if (StringUtils.isNotBlank(String.valueOf(voObject.getClass().getDeclaredField(value.toString())))) {
                                if (StringUtils.isNotBlank((String) invokeGet(voObject, value.toString()))) {
                                    if (dictionaryDetailList != null && dictionaryDetailList.size() > 0) {
                                        for (DictionaryDetail d : dictionaryDetailList) {
                                            if (StringUtils.equals((String) invokeGet(voObject, value.toString()), d.getDetailCode())) {
                                                invokeSet(voObject, valueStr, d.getDetailName());
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }
                }
                listVO.add(voObject);
            }
        }
        return listVO;
    }

    /**
     * 执行set方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     * @param value     值
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }
}