package j2se.collection;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonDemo {
    public static void main(String[] args) {
        // TODO: 2018-03-12 判断JSON中的属性值是否全部为空
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", "2");
        jsonObject.put("2", "3");
        String value = "";
        Set<Map.Entry<String, Object>> s = jsonObject.entrySet();
        for (Iterator<Map.Entry<String, Object>> iterator = s.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> next = iterator.next();
            value += (String) next.getValue();
        }
        System.out.println(value);
    }

    public void jsonDemo() {
        // TODO: 2018-03-12 json数组转list
        JSONObject jsonObject = JSONObject.parseObject("{formItem:[{formModalID:xx,a:x,b:x},{formModalID:xx,a:x,b:x}]");//获取jsonoArray对象
        JSONArray array = jsonObject.getJSONArray("formItem");
        List<Map<String, String>> listMap = JSON.parseObject(array.toJSONString(), new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> map : listMap) {
        }
    }
}
