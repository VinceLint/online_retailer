package cn.neusoft.retailer.web.interceptor;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.tools.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/29 18:54
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisClient redisClient;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        //校验
        Map<String, String> result = new HashMap<>();
        String cookie = request.getHeader("Cookie");
        User user = null;

        if (cookie.contains("token")) {

            String[] cookieInfo = cookie.split(";");
            String tokenMessage = null;
            for (String s : cookieInfo) {
                if (s.contains("token")) {
                    tokenMessage = s;
                }
            }
            String[] tokenInfo = tokenMessage.split("=");
            String token = tokenInfo[1];

            try {
                user = redisClient.findAndUpdate(token, request.getRemoteAddr(), (Boolean) request.getSession(false).getAttribute("flag"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }
}
