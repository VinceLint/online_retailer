package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParameterMapper {
    int deleteByPrimaryKey(String parId);

    int insert(Parameter record);

    Parameter selectByPrimaryKey(String parId);

    List<Parameter> selectAll();

    int updateByPrimaryKey(Parameter record);

    List<Parameter> selectByPage(@Param("pageNum") Integer pageNum);

}