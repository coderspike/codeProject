package thread.ThreadSchool.threadLocal;

public class RequestHolder {

    /*
    线程安全，线程封闭 ThreadLocal 安全好用，线程安全
    ThreadLocal 内部是一个map key是线程名称  map的值是线程对象
     */
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
