package j2se.Enum;

/**
 * 用枚举定义常量，项目开发中可用
 * 所有常量类型都是引用类型
 * 可以用于switch
 */
public enum Color {
    RED("红色"),BLUE("蓝色"),BLACK("黑色");

    private String chinese;

    Color(String chinese) {
        this.chinese = chinese;
    }
}
