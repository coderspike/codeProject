package j2se.aboutClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class aboutClass {
    public aboutClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*
        创建对象的几种方式
        1 new的方式
        */
        Father father = new Father();
        /*
        2 newInstance的方式1
        */
        Father father2 = Father.class.newInstance();
        /*
        3 newInstance的方式2
        */
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

    /*
    4 Constructor类的newInstance
     */
    Constructor<Father> constructor = Father.class.getConstructor();
    Father stu = constructor.newInstance();

    //5 范序列化 比如Jackson 、 Gson实现的json反序列化等
    //  ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
    //  Employee emp5 = (Employee) in.readObject();

    //6 使用clone方法
    //  Employee emp4 = (Employee) emp3.clone();
}
