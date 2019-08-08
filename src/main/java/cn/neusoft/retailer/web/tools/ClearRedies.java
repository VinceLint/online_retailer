package cn.neusoft.retailer.web.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/8/8 11:14
 * 清除redis缓存
 */
public class ClearRedies {
    private RedisProperties redisProperties;

    public void clearAll(){
        ApplicationContext context= new ClassPathXmlApplicationContext("spring-redis.xml");
        JedisPoolConfig jedisConfig = (JedisPoolConfig) context.getBean("jedisConfig");
        JedisPool jedisPool = new JedisPool(jedisConfig,redisProperties.getHost(),redisProperties.getPort());
        int DB_INDEX = 1;
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = jedisPool.getResource();
//            jedis.select(DB_INDEX);
//            清除缓存
            System.out.println("清除缓存" );
            jedis.flushDB();
            jedis.flushAll();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
                jedisPool.returnBrokenResource(jedis);
        } finally {
            if (borrowOrOprSuccess)
                jedisPool.returnResource(jedis);
        }
    }
}
