package cn.neusoft.retailer.web.interceptor;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.tools.UserPrivilege;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/30 21:04
 */
public class MvoInterceptor implements HandlerInterceptor {

    /**
     * @描述 品牌商请求拦截器，拦截所有关于品牌商订单管理的请求，判断用户访问权限
     * @参数
     * @返回值 是否继续执行请求
     * @创建人 胡献涛
     * @创建时间  2019/7/30 21:05
     * @修改人和其它信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user==null||user.getUserPrivilege()!= UserPrivilege.品牌商.ordinal()){
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
