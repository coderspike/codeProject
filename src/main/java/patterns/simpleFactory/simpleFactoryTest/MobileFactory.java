package patterns.simpleFactory.simpleFactoryTest;

public class MobileFactory {
    public static Mobile chooseMobile(String mobile) {
        Mobile mobilePhone = null;
        if ("apple".equals(mobile)) {
            mobilePhone = new Apple();
        } else if ("onePlus".equals(mobile)) {
            mobilePhone = new OnePlus();
        } else if ("smartisan".equals(mobile)) {
            mobilePhone = new Smartisan();
        }
        return mobilePhone;
    }

    public static void main(String[] args) {
        String mobile = "smartisan";
        Mobile mobilePhone = chooseMobile(mobile);
        mobilePhone.getMobile();
    }
}
