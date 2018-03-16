package cache;

import base.DictionaryDetail;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类用于存放和取出ehcache缓存， 存放 菜单 数据字典等 一些经常查询但是极少数修改的数据
 */
public class EhcacheManager {
    private static CacheManager manager;
    private static Cache cache;

    static {
        init();//调用ehcache配置文件，实例化cache
    }

    private static void init() {
        System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
        //获取ehcache.xml配置文件
        URL url = EhcacheManager.class.getResource("/ehcache.xml");
        manager = new CacheManager(url);
        //根据缓存库（testCache，配置文件中的缓存库配置）实例化cache
        cache = manager.getCache("userCache");
        Element element = new Element("123", "1234");//根据键值对在缓存里存放数据
        cache.put(element);
    }

    private static void stop() {
        if (manager != null) {
            manager.shutdown();
            manager = null;
        }
    }

    /**
     * 获取缓存中的数据
     *
     * @param key 键
     * @return
     */
    public synchronized static Object get(Serializable key) {
        return get(Object.class, key);
    }

    /**
     * @param key
     * @return Map<String,String>
     * @Description:获取缓存中字典项的值和code，传入字典项code
     */
    public synchronized static Map<String, String> getDictionaryMap(Serializable key) {
        List<DictionaryDetail> de = (List<DictionaryDetail>) EhcacheManager.get(key);
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (de != null && de.size() > 0) {
            for (DictionaryDetail d : de) {
                map.put("testEhcache", "testEhcache");
                map.put(d.getDetailCode(), d.getDetailName());
            }
        }
        return map;
    }

    /**
     * @param key
     * @return Map<String,String>
     * @Description:获取缓存中字典值的项
     */
    public synchronized static Map<String, String> getDictionaryValMap(Serializable key) {
        List<DictionaryDetail> de = (List<DictionaryDetail>) EhcacheManager.get(key);
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (de != null && de.size() > 0) {
            for (DictionaryDetail d : de) {
                map.put(d.getDetailName(), d.getDetailCode());
            }
        }
        return map;
    }

    /**
     * 获取缓存中的数据
     *
     * @param <T>
     * @param resultClass
     * @param key         键
     * @return
     */
    @SuppressWarnings("unchecked")
    public synchronized static <T> T get(Class<T> resultClass, Serializable key) {
        Element element = cache.get(key);//根据缓存中存入的键值，得到缓存内容
        if (element != null) {
            T value = (T) element.getObjectValue();//实例化缓存对象
            return value;
        }
        return null;
    }

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     */
    public synchronized static void put(Object key, Object value) {
        put((Serializable) key, (Serializable) value);
    }

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     */
    public synchronized static void put(Serializable key, Serializable value) {
        Element element = new Element(key, value);//根据键值对在缓存里存放数据
        cache.put(element);
    }

    /**
     * 存入
     *
     * @param <T>
     * @param key               键
     * @param value             值
     * @param timeToLiveSeconds 缓存最大存活时间
     * @param timeToIdleSeconds 缓存最大访问间隔时间
     */
    public synchronized static <T extends Serializable> void put(String key, T value, int timeToLiveSeconds, int timeToIdleSeconds) {
        Element element = new Element(key, value);
        element.setTimeToLive(timeToLiveSeconds);
        element.setTimeToIdle(timeToIdleSeconds);
        cache.put(element);
    }

    /**
     * 清除缓冲中的某个数据
     *
     * @param key 键
     */
    public synchronized static void remove(Serializable key) {
        cache.remove(key);
    }

    /**
     * 清除缓冲中的所有数据
     */
    public synchronized static void clear() {
        cache.removeAll();
    }

}
