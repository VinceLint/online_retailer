package cn.neusoft.retailer.web.tools;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/8/8 0:21
 * pojo的序列化工具
 */

import java.io.*;

/**
 *@Title SerializeUtils.java
 *@description:  序列化
 **/
public class SerializeUtil {


    /**
     * @Title: serialize
     * @Description: 序列化
     * @return byte[]
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Title: unserialize
     * @Description: 反序列化
     * @return Object
     */
    public static Object unserialize(byte[] bytes) {
        if (bytes == null)
            return null;
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
