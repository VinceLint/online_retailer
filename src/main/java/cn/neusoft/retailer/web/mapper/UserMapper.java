package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    boolean insert(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByName(String userName);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(String userName);

    User selectByEngName(String userName);
}