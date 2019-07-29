package cn.neusoft.retailer.web.tools;

import org.junit.Test;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/24 21:49
 */
public class UniqueID {

    // 位数，默认是8位
    private final static long w = 100000;
    public static int Guid = 100;
    /**
     * @描述: 利用6位时间戳+3位自增数(9位(Interger范围-2147483648——2147483641))实现伪随机数
     * @参数:
     * @返回值: 9位数字的Integer
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/24
     */
    private static byte[] lock = new byte[0];

    //根据时间戳与随机数创建唯一ID
    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }

    public static Integer getGuid() {

        UniqueID.Guid += 1;

        long now = System.currentTimeMillis();
        //获取时间戳
        String info = now + "";

        if (UniqueID.Guid > 999) {
            UniqueID.Guid = 100;
        }

        return Integer.valueOf(info.substring(7, info.length()) + Guid);
    }

    @Test
    public void test() {
        System.out.println(getGuid());
    }
}
