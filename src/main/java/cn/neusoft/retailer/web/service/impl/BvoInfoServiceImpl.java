package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BvoInfoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 8:39
 */
@Service
public class BvoInfoServiceImpl implements BvoInfoService {
    @Autowired
    private UserMapper userMapper;
    /**
     *@描述  根据id查找出该用户的信息
     *@参数  整型对象
     *@返回值  User对象
     *@创建人  林跃涛
     *@创建时间  2019/8/3 8:40
     *@修改人和其它信息
     */
    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


    /**
     *@描述  更新借卖方信息
     *@参数  JSONObject对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/8/3 9:22
     *@修改人和其它信息
     */
    @Override
    public boolean update(JSONObject jsonObject, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setMvoEngName(jsonObject.getString("name"));
        user.setUserMail(jsonObject.getString("email"));
        user.setUserPhone(jsonObject.getString("phone"));
        if (userMapper.updateByPrimaryKey(user))return true;
        return false;
    }
}
