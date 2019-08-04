package cn.neusoft.retailer.web.interceptor;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.tools.UserPrivilege;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/2 11:06
 */
public class BvoInterceptor implements HandlerInterceptor {
    /**
     *@描述  拦截借卖方的请求
     *@参数
     *@返回值  是否继续执行
     *@创建人  林跃涛
     *@创建时间  2019/8/2 11:06
     *@修改人和其它信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user==null||user.getUserPrivilege()!= UserPrivilege.借卖方.ordinal()){
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
