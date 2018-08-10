package j2se.collection;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        /*
          public interface Map<K,V>  map接口
          Map接口中包含了一个keySet()方法，用于返回Map中所有key组成的Set集合。
          Map ：基于Map接口实现、允许null键/值、非同步、不保证有序(比如插入的顺序)、也不保证序不随时间变化。
          --| HashMap 基于hash表
          --| TreeMap 基于红黑树
          --| LinkedHashMap 有序的HashMap
         */
        Map m = new HashMap();
    }
}
