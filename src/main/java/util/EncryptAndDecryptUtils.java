package util;

/**
 * @ClassName EncryptAndDecryptUtils
 * @Description TODO
 * @Author 39446
 * @Date 2018/8/20 17:20
 * @Version 1.0
 **/
public class EncryptAndDecryptUtils {
    /**
     * MD5 加密
     *
     * @param value 待加密字符
     * @return
     * @author : chenssy
     * @date : 2016年5月20日 下午4:54:23
     */
    public static String md5Encrypt(String value) {
        String result = null;
        if (value != null && !"".equals(value.trim())) {
            result = MD5Utils.encrypt(value, MD5Utils.MD5_KEY);
        }
        return result;
    }

    /**
     * SHA加密
     *
     * @param value 待加密字符
     * @return 密文
     * @author : chenssy
     * @date : 2016年5月20日 下午4:59:42
     */
    public static String shaEncrypt(String value) {
        String result = null;
        if (value != null && !"".equals(value.trim())) {
            result = MD5Utils.encrypt(value, MD5Utils.SHA_KEY);
        }
        return result;
    }

    /**
     * BASE64 加密
     *
     * @param value 待加密字符串
     * @return
     * @author : chenssy
     * @date : 2016年5月20日 下午5:16:12
     */
    public static String base64Encrypt(String value) {
        String result = null;
        if (value != null && !"".equals(value.trim())) {
            result = Base64Utils.encrypt(value.getBytes());
        }
        return result;

    }

    /**
     * BASE64 解密
     *
     * @param value 待解密字符串
     * @return
     * @author : chenssy
     * @date : 2016年5月20日 下午5:16:34
     */
    public static String base64Decrypt(String value) {
        String result = null;
        try {
            if (value != null && !"".equals(value.trim())) {
                byte[] bytes = Base64Utils.decrypt(value);
                result = new String(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES加密<br>
     *
     * @param value 待加密字符
     * @param key   若key为空，则使用默认key
     * @return 加密成功返回密文，否则返回null
     * @author : chenssy
     * @date : 2016年5月20日 下午5:39:46
     */
    public static String desEncrypt(String value, String key) {
        key = key == null ? DESUtils.KEY : key;
        String result = null;

        try {
            if (value != null && !"".equals(value.trim())) {
                result = DESUtils.encrypt(value, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES解密
     *
     * @param value 待解密字符
     * @param key   若key为空，则使用默认key
     * @return
     * @author : chenssy
     * @date : 2016年5月20日 下午5:55:56
     */
    public static String desDecrypt(String value, String key) {
        key = key == null ? DESUtils.KEY : key;
        String result = null;

        try {
            if (value != null && !"".equals(value.trim())) {
                result = DESUtils.decrypt(value, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * AES加密
     *
     * @param value 待加密内容
     * @param key   秘钥
     * @return
     * @author:chenssy
     * @date : 2016年5月21日 上午9:58:58
     */
    public static String aesEncrypt(String value, String key) {
        key = key == null ? AESUtils.KEY : key;
        String result = null;
        try {
            if (value != null && !"".equals(value.trim())) {        //value is not null
                result = AESUtils.encrypt(value, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * AES解密
     *
     * @param value 待解密内容
     * @param key   秘钥
     * @return
     * @author:chenssy
     * @date : 2016年5月21日 上午10:02:07
     */
    public static String aesDecrypt(String value, String key) {
        key = key == null ? AESUtils.KEY : key;
        String result = null;
        try {
            if (value != null && !"".equals(value.trim())) {        //value is not null
                result = AESUtils.decrypt(value, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
