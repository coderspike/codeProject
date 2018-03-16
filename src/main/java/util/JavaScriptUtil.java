package util;

public class JavaScriptUtil {
//    // 创建脚本引擎管理器
//    public static final ScriptEngineManager factory = new ScriptEngineManager();
//    // 创建JavaScript引擎
//    public static final ScriptEngine engine = factory.getEngineByName("JavaScript");
//    private static final Gson G = new Gson();
//
//    /**
//     * js校验
//     *
//     * @param obj        数据
//     * @param rule       规则
//     * @param columnName 名称
//     * @return
//     * @throws ScriptException
//     */
//    public static String exceteJS(Object obj, String rule, String columnName) throws ScriptException {
//        String warning = "";
//        JSONObject jObj = JSON.parseObject(JSON.toJSONString(obj));
//        //if(jObj.containsKey(columnName)){
//        warning = String.valueOf(engine.eval("(" + rule + ")('" + jObj.get(columnName) + "')"));
//        //}
//        return warning;
//    }
//
//    /**
//     * js转换
//     *
//     * @param obj  数据
//     * @param rule 规则
//     * @return String
//     * @throws ScriptException
//     */
//    public static String exceteJS(Object obj, String rule) throws ScriptException {
//        String warning = "";
//        warning = String.valueOf(engine.eval("(" + rule + ")('" + obj + "')"));
//        return warning;
//    }
//
//    /**
//     * @param script
//     * @param param
//     * @return Object
//     * @Description:js调用执行代码
//     * @date 2017年12月11日
//     */
//    public static Object execScript(String script, Map<String, Object> param) {
//        try {
//            for (String key : param.keySet()) {
//                String json = G.toJson(param.get(key));
//                String ss = "var " + key + " = " + json + ";";
//                engine.eval(ss);
//            }
//            return engine.eval(script);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        JSONObject jj = new JSONObject();
//        jj.put("name", "12");
//        String warning = "";
//        try {
//            warning = exceteJS(jj, "function checkNum(value){var reg = /[0-9\\.]+/g;if(reg.test(value)){return true;}else{ return '只能输入数字！';}}", "name");
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//        System.out.println(warning);
//    }
}
