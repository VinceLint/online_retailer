package cn.neusoft.retailer.web.tools;

import cn.neusoft.retailer.web.pojo.User;

import java.io.*;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/26 13:19
 */
public class SerializeUtils {
    public static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String string = byteArrayOutputStream.toString("UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return string;
    }

    public static Object serializeToObject(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = (User) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }
}
