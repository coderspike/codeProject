package util;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @ClassName SensitiveJudgeUtil
 * @Description TODO
 * @Author 39446
 * @Date 2018/8/21 10:16
 * @Version 1.0
 **/
public class SensitiveJudgeUtil {
    //最小匹配规则
    public static int minMatchTYpe = 1;

    //词组最短判断长度
    public static int judgeLength = 2;

    public static void main(String[] args) {
        //待审核字段
        String string = "太多的伤感情怀也许法轮功只局限于饲养基地草荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "解放军飒飒的房间阿三地方就ask地方书法老师看见gals的价格啊是根据爱上了打开挂机啊阿斯顿噶就是打飞机啊是的"
                + "vvv啊手动阀手动阀手动阀手动阀开始的发士大夫啊撒旦解放阿三地方就阿斯达克放假啊是雷达发射发生打法士大夫"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法"
                + "卡夫卡快发快发快发快发快发快发发爱上了的方法爱上了对方卡萨丁空IP请问欧日上的噶山豆根静安寺打法日本人";
        //敏感词库
        HashSet keyWordSet = Sets.newHashSet("日本人", "法轮功");

        //使用方法
        Boolean isSensitive = SensitiveJudgeUtil.containSensitiveWord(string, keyWordSet);
        System.out.println("是否包含敏感信息（默认词组最短为2）：" + isSensitive);

        //性能测试
        System.out.println("-----------------性能测试---------------------");
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        Boolean sensitive = SensitiveJudgeUtil.containSensitiveWord(string, keyWordSet);
        long endTime = System.currentTimeMillis();
        System.out.println("是否含有敏感词：" + sensitive);
        System.out.println("耗时：" + (endTime - beginTime) + "ms");

        //其余方法
        System.out.println("------------------替换等方法--------------------");
        HashMap sensitiveWordMap = SensitiveJudgeUtil.initSensitiveWordToHashMap(keyWordSet);
        Set<String> set = SensitiveJudgeUtil.getSensitiveWord(string, 2, sensitiveWordMap);
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        string = SensitiveJudgeUtil.replaceSensitiveWord(string, 2, "*", sensitiveWordMap);
        System.out.println("替换后：" + string);
    }

    /**
     * 初始化敏感词库
     */
    private static HashMap initSensitiveWordToHashMap(HashSet keyWordSet) {
        //将敏感词库加入到HashMap中，初始化敏感词容器，减少扩容操作
        HashMap sensitiveWordMap = new HashMap(keyWordSet.size());
        String key;
        Map nowMap;
        Map<String, String> newWorMap;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            //关键字
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //获取
                Object wordMap = nowMap.get(keyChar);
                //如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) {
                    //最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
        return sensitiveWordMap;
    }

    /**
     * 替换敏感字符串
     *
     * @param txt
     * @param matchType
     * @param replaceChar
     * @param sensitiveWordMap
     * @return
     */
    public static String replaceSensitiveWord(String txt, int matchType, String replaceChar, HashMap sensitiveWordMap) {
        String resultTxt = txt;
        //获取所有的敏感词
        Set<String> set = getSensitiveWord(txt, matchType, sensitiveWordMap);
        Iterator<String> iterator = set.iterator();
        String word;
        String replaceString;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }

    /**
     * 替换字符操作
     *
     * @param replaceChar
     * @param length
     * @return
     */
    private static String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 判断是否含有敏感词
     *
     * @param txt
     * @param
     * @return
     */
    public static boolean containSensitiveWord(String txt, HashSet keyWordSet) {
        //初始化敏感词库
        HashMap sensitiveWordMap = SensitiveJudgeUtil.initSensitiveWordToHashMap(keyWordSet);
        //默认最小匹配策略
        int matchType = minMatchTYpe;
        boolean flag = false;
        labelB:
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int matchFlag = SensitiveJudgeUtil.CheckSensitiveWord(txt, i, matchType, sensitiveWordMap);
            //大于0存在，返回true
            if (matchFlag > 0) {
                flag = true;
                break labelB;
            }
        }
        return flag;
    }

    /**
     * 获取中文敏感词
     *
     * @param txt
     * @param matchType
     * @return
     */
    public static Set<String> getSensitiveWord(String txt, int matchType, HashMap sensitiveWordMap) {
        Set<String> sensitiveWordList = new HashSet<>();
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int length = CheckSensitiveWord(txt, i, matchType, sensitiveWordMap);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 检查敏感字符的具体逻辑
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return
     */
    public static int CheckSensitiveWord(String txt, int beginIndex, int matchType, HashMap sensitiveWordMap) {
        //敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            //遍历字符串的每一个字符
            word = txt.charAt(i);
            //每一个字符去比对是否能对应上敏感词树
            nowMap = (Map) nowMap.get(word);
            //存在，则判断是否为最后一个
            if (nowMap != null) {
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if (SensitiveJudgeUtil.minMatchTYpe == matchType) {
                        break;
                    }
                }
            } else {
                //不存在，直接返回
                break;
            }
        }
        //长度必须大于等于1，为词
        if (matchFlag < judgeLength || !flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
}
