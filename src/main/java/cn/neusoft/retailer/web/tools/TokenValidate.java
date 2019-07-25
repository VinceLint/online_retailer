package cn.neusoft.retailer.web.tools;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 2:00
 */
public class TokenValidate {

    @Autowired
//    private StringRedisTemplate redisTemplate;

    private static final String MANAGE_TOKEN_PREFIX = "gmdropship:manageuser:token:";
}
