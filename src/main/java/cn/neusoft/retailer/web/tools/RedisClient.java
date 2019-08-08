package cn.neusoft.retailer.web.tools;

import cn.neusoft.retailer.web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springmvc.xml"})

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 2:00
 */
@Component
public class RedisClient {

    private static final long TOKEN_EXPIRES_SECOND = 30 * 60;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @描述: token存入Redis
     * @参数: [key, user]
     * @返回值: boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/28
     */
    public boolean set(String key, User user) {
        boolean result = false;
        try {

            redisTemplate.opsForValue().set(key, user, TOKEN_EXPIRES_SECOND, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean Set(String key, User user, long expireTime) {
        boolean result = false;
        try {

            redisTemplate.opsForValue().set(key, user, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @描述: 校验token并更新(非 " 记住我 " 方式登录时的页面跳转)
     * @参数: [key, ip, flag]
     * @返回值: cn.neusoft.retailer.web.pojo.User
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/28
     */
    public User findAndUpdate(String key, String ip, Boolean flag) {

        //判断Cookies是否被盗窃
        String info = BASE64.decryptBASE64(key);
        if (!info.substring(44, info.length()).equals(ip)) {
            return null;
        }

        //判断token是否过期
        User user = null;
        try {
            user = (User) redisTemplate.opsForValue().get(key);
            if (user == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //更新token时长
        if (!flag) {
            set(key, user);
        }
        return user;
    }

    /**
     * @描述: 移除Redis中的token
     * @参数: [key]
     * @返回值: boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/28
     */
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
}
