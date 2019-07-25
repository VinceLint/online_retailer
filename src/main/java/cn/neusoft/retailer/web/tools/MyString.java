package cn.neusoft.retailer.web.tools;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 22:12
 */
//一些处理String的工具
public class MyString {
    //判断是否是汉字
    public static boolean ifchinese(java.lang.String string) {
        java.lang.String reg = "[\\u4e00-\\u9fa5]+";
        return string.matches(reg);
    }

    //判断是否为英文名
    public static boolean ifEnglish(java.lang.String string){
        java.lang.String reg = "[a-zA-Z]+";
        return string.matches(reg);
    }

    //判断是否全为数字
    public static boolean ifNumber(java.lang.String string){
        java.lang.String reg = "[0-9]+";
        return string.matches(reg);
    }

}
