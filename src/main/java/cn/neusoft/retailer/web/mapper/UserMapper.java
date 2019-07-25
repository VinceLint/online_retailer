package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByName(String userName);

    User selectByEngName(String userName);
}