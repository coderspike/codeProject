package j2se.aboutClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class aboutClass {
    public aboutClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // TODO: 2018-03-11 创建对象的几种方式
        //1 new
        Father father = new Father();

        //2 newInstance
        Father father2 = Father.class.newInstance();

        //3 newInstance
        try {
            Father father3 = (Father) Class.forName("com.j2se.aboutClass.Father").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //4 Constructor类的newInstance
    Constructor<Father> constructor = Father.class.getConstructor();
    Father stu = constructor.newInstance();

    //5 范序列化 比如Jackson 、 Gson实现的json序列化等
}
