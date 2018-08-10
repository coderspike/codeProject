package patterns.Adapter;

/**
 * 待适配类
 */
public class Adaptee {
    public void onRequest() {
        System.out.println("Adaptee.onRequest()");
    }
}