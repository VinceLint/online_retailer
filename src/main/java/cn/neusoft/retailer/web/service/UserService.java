package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.User;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/22 12:52
 */
public interface UserService {
    User selectByPrimaryKey(Integer userId);

}
