package cn.neusoft.retailer.web.tools;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/8/8 11:28
 * redis配置数据
 */
public class RedisProperties {
    //    redis配置
    private static final String host="47.100.102.99";
    private static final int port=6379;

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }
}
