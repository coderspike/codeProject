package patterns.Decorator;

public class Test {
    public static void main(String[] args) {
        Girl g1 = new AmericanGirl();
        System.out.println(g1.getDescription());
        GoldenHair g2 = new GoldenHair(g1);
        System.out.println(g2.getDescription());
        Tall g3 = new Tall(g2);
        System.out.println(g3.getDescription());
        // 你也可以一步到位
        // Girl g = new Tall(new GoldenHair(new AmericanGirl()));
    }

/**
 *   装饰者模式应用
 　　当你需要动态地给一个对象添加功能，实现功能扩展的时候，就可以使用装饰者模式。
 　　Java IO 类中有一个经典的装饰者模式应用， BufferedReader 装饰了 InputStreamReader.
 　　BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
 　　InputStreamReader(InputStream in) - InputSteamReader 读取 bytes 字节内容，然后转换成 characters 流 输出。
 　　BufferedReader(Reader in) - 从 characters 流 中读取内容并缓存。
 */

}
