package cn.neusoft.retailer.web.tools;

import org.junit.Test;

import java.util.UUID;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 12:04
 */
public class TokenCreation {

    /**
     * @描述: 8位时间戳作为登录序列(substring ( 0, 8))，后几位为ip地址(substring(44,.length()))
     * @参数: [ip]
     * @返回值: java.lang.String
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */
    public static String createToken(String ip) {
        String timestamp = System.currentTimeMillis() + "";
        String token = timestamp.substring(5, timestamp.length()) + UUID.randomUUID().toString() + ip;
        System.out.println(token);
        return BASE64.encryptBASE64(token).replace("=", "-");
    }

    @Test
    public void test() {
        String token = createToken("127.0.0.1");
        System.out.println(token);
        System.out.println(BASE64.decryptBASE64(token));
    }
}
