package interview;

public class StrPass {
    public void fun(String str) {
        str = " 张三 ";
        System.out.println(str);//张三
    }

    public static void main(String args[]) {
        StrPass t = new StrPass();
        String str = " 李四 ";
        t.fun(str);
        System.out.println(str);//李四
    }
}

/*
java中方法的参数传递为值传递，不管这个参数是基本类型还是引用类型，我们传递过去的都是该值的一个副本，这个副本作为方法的局部变量保存在栈（stack）中。
对于上面的 changeValue() 方法，传递进来的是变量 value 的一个副本，在 changeValue() 方法中修改的只是该方法的一个局部变量，不会影响作为参数传进来的那个变量，两者井水不犯河水。
 */