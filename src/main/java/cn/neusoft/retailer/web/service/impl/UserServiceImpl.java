package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/22 12:52
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * @描述:用户登录
     * @参数: [userName, userPassword]
     * @返回值: cn.neusoft.retailer.web.pojo.User
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/23
     */
    @Override
    public User login(String userName, String userPassword) {
        return null;
    }

}
