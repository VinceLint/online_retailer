package cn.neusoft.retailer.web.tools;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 1:06
 */
public class MD5 {

    public static String encrypt(String info) {
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(info.getBytes());
            byte s[] = messageDigest.digest();
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(MD5.encrypt("admin"));
    }
}
