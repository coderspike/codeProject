package j2se.aboutClass.enumDemo;

/**
 * 关于颜色的枚举.
 */
public enum ColorEnum {

    RED(1, "红色"), GREEN(2, "绿色"), BLUE(3, "蓝色");

    /**
     * 颜色的code.
     */
    private int code;

    /**
     * 颜色的名称.
     */
    private String name;

    /**
     * 枚举的构造方法默认且只能是private的.
     *
     * @param code 代码值
     * @param name 名称
     */
    ColorEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据颜色的code值获取到对应的名称.
     * 不知道可不可以用作数据字典
     *
     * @param code 颜色code
     * @return 颜色名称
     */
    public static String getNameByCode(int code) {
        for (ColorEnum color : ColorEnum.values()) {
            if (color.code == code) {
                return color.name;
            }
        }
        return null;
    }

    /**
     * 覆盖的toString方法.
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }

    /* getter方法. */
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}