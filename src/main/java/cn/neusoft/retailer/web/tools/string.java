package cn.neusoft.retailer.web.tools;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 22:12
 */
//一些处理String的工具
public class string {
    //判断是否是汉字
    public static boolean ifchinese(String string) {
        String reg = "[\\u4e00-\\u9fa5]+";
        return string.matches(reg);
    }

    //判断是否为英文名
    public static boolean ifEnglish(String string) {
        String reg = "[a-zA-Z]+";
        return string.matches(reg);
    }

    //判断是否全为数字
    public static boolean ifNumber(String string) {
        String reg = "[0-9]+";
        return string.matches(reg);
    }
    
    /** 
    * @描述: 判断各类参数
    * @参数:  
    * @返回值: boolean
    * @创建人: 罗圣荣
    * @创建时间: 2019/7/24 
    */ 
    //判断是否符合email格式
    public static boolean isEmail(String email) {

        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

        Matcher matcher = emailPattern.matcher(email);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testEmail(){
        System.out.println(isEmail("abc@qq.com"));
    }

    //判断是否符合密码格式
    public static boolean isPassword(String passwd) {
        Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$");
        Matcher matcher = Password_Pattern.matcher(passwd);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testPasswd(){
        System.out.println(isPassword("!!!1!dfs"));
    }

    //判断是否符合url格式
    public static boolean isURL(String str){
        if (str == null){
            return true;
        }
        //转换为小写
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)" //https、http、ftp、rtsp、mms
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184  
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        if (str.matches(regex)){
            return true;
        }else{
            return false;
        }
    }

    @Test
    public void testUrl(){
        System.out.println(isURL(null));
    }
}
