package cn.neusoft.retailer.web.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 21:31
 */
public class BASE64 {

    public static String encryptBASE64(String key) {
        byte[] bytes = key.getBytes();
        try {
            return (new BASE64Encoder()).encodeBuffer(bytes).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptBASE64(String key) {
        byte[] bytes;
        try {
            bytes = (new BASE64Decoder()).decodeBuffer(key);
            String info = new String(bytes);
            return info.substring(0, info.length() - 1).replace("\n", "").replace("\t", "").replace("\r", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
