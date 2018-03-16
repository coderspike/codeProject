package j2se.collection;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) {
        // 线程不安全
        Map map = new HashMap<String, String>();
        map.put(1, 1);
        map.put(1, 3); //不能包含重复键,后面的会覆盖前面的
        map.put(2, 2);
        map.put(null, 2);//允许key为null 其实就是放在了第0个位置
        // TODO: 2018-03-11 遍历HashMap 
        //Set<Map.Entry<K, V>> entrySet();
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(value);
        }
        // TODO: 2018-03-11 转化成数组
        //HashMap内部存储的数据是无序的，这是因为HashMap内部的数组的下表是根据hash值算出来的
        Object[] objects = map.entrySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }

    /**
     * map排序，按照value数值倒序排序
     *
     * @param oldMap
     * @return
     */
    public static Map<String, Integer> sortMap(Map<String, Integer> oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg1.getValue() - arg0.getValue();
            }
        });
        Map<String, Integer> newMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }
}
