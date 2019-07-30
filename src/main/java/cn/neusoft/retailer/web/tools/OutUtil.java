package cn.neusoft.retailer.web.tools;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/26 15:07
 */
public class OutUtil {
    public static void print(Object o, HttpServletResponse response) {
        //解决ajax中文乱码
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(o);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
