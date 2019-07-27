package cn.neusoft.retailer.web.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springmvc.xml"})

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 2:00
 */
public class RedisClient {

    public static final long TOKEN_EXPIRES_SECOND = 30 * 60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @描述: 存入Redis
     * @参数: [key, value]
     * @返回值: boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */

    public boolean set(String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean set(String key, String value, long expireTime) {
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
     * @描述: 验证token, 若是页面跳转且成功(flag = true),增加token生命周期30mins；若是"记住我"登录(flag = false),token时长不增加;
     * @参数: [key, ip, flag]
     * @返回值: java.lang.String
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/27
     */
    public String findAndUpdate(String key, String ip) {

        //判断Cookies是否被盗窃
        String info = BASE64.decryptBASE64(key);
        if (!info.substring(44, info.length()).equals(ip)) {
            return null;
        }

        //判断token是否过期
        String user = null;
        try {
            user = redisTemplate.opsForValue().get(key);
            if (user == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //更新token时长
        set(key, user);
        return user;
    }


    public boolean remove(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(redisTemplate);
    }
}
