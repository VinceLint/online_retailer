package cn.neusoft.retailer.web.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 2:00
 */
public class RedisClient {

    public static final long TOKEN_EXPIRES_SECOND = 30 * 60;

    @Autowired
    private static StringRedisTemplate redisTemplate;

    /**
     * @描述: 存入Redis
     * @参数: [key, value]
     * @返回值: boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */

    public static boolean set(String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean set(String key, String value, long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @描述: 验证token并更新其生命周期
     * @参数: [key]
     * @返回值: java.lang.String
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */
    public static String findAndUpdate(String key, String ip) {

        //判断Cookies是否被盗窃
        String info = BASE64.decryptBASE64(key);
        if (!info.substring(44, info.length()).equals(ip)) {
            return null;
        }

        //判断token是否过期
        String userName = null;
        try {
            userName = redisTemplate.opsForValue().get(key);
            if (userName == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //更新token时长
        set(key, userName);
        return userName;
    }

    public static boolean remove(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
