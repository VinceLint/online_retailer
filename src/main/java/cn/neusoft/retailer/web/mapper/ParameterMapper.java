package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Parameter;
import java.util.List;

public interface ParameterMapper {
    int deleteByPrimaryKey(String parId);

    int insert(Parameter record);

    Parameter selectByPrimaryKey(String parId);

    List<Parameter> selectAll();

    int updateByPrimaryKey(Parameter record);
}