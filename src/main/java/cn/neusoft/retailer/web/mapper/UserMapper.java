package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(@Param(value = "userId") Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}