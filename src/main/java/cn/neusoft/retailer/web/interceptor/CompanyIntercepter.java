package cn.neusoft.retailer.web.interceptor;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.tools.UserPrivilege;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-08-06 11:05
 * 用于品牌商权限，需要管理员和品牌商权限之一
 */
public class CompanyIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user==null||(user.getUserPrivilege()!= UserPrivilege.品牌商.ordinal()&&user.getUserPrivilege()!= UserPrivilege.系统管理员.ordinal())){
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
