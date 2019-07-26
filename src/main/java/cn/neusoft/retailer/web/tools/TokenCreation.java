package cn.neusoft.retailer.web.tools;

import org.junit.Test;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 12:04
 */
public class TokenCreation {

    public static String createToken(String userName) {
        String timestamp = System.currentTimeMillis() + "";
        String token = (MD5.encrypt(timestamp).substring(1, 8) + MD5.encrypt(userName));
        return token;
    }

    @Test
    public void test() {
        System.out.println(createToken("admin"));
    }
}
