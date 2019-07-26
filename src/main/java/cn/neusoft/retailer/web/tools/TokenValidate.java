package cn.neusoft.retailer.web.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 2:00
 */
public class TokenValidate {

    private static final String TOKEN_PREFIX = "gmdropship:vadidation:token:";
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @描述: Redis保存token（30mins）
     * @参数: [token, id]
     * @返回值: void
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */
    public void saveManageUser(String token, Integer id) {
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, id + "", 30 * 60, TimeUnit.SECONDS);
    }

    /**
     * @描述: 验证token并更新其生命周期
     * @参数: [token]
     * @返回值: java.lang.Long
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/26
     */
    public Long findManageUserAndUpdate(String token) {
        String userId = redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        if (userId == null) {
            return 0L;
        }
        saveManageUser(token, Integer.valueOf(userId));
        return Long.parseLong(userId);
    }
}
