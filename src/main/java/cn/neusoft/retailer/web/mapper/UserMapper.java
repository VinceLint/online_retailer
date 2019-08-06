package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    boolean insert(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByName(String userName);

    List<User> selectAll();

    boolean updateByPrimaryKey(User record);

    boolean updateByName(User record);

    User selectByEngName(String userName);

    boolean updateByPrimaryKey_NoPassword(User record);

}