package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User selectByPrimaryKey(Integer userId) throws Exception {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User selectByName(String userName) { return selectByName(userName); }

    @Override
    public User selectByEnglishName(String englishName) {
        return selectByEnglishName(englishName);
    }

    @Override
    public List<User> selectAll() {
        return selectAll();
    }

    @Override
    public boolean insertByUserInfo(User user) {
        return true;
    }

    @Override
    public boolean updateByPrimaryKey(User user) { return userMapper.updateByPrimaryKey(user) != 0; }

    @Override
    public boolean deleteByPrimaryKey(Integer userId) { return userMapper.deleteByPrimaryKey(userId) != 0; }

}
