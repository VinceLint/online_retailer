package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.ParameterMapper;
import cn.neusoft.retailer.web.pojo.Parameter;
import cn.neusoft.retailer.web.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/29 15:16
 */
@Service
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterMapper parameterMapper;
    /**
     *@描述  根据页号来查询对应的记录
     *@参数  整型对象
     *@返回值  参数列表对象
     *@创建人  林跃涛
     *@创建时间  2019/7/29 15:24
     *@修改人和其它信息
     */
    @Override
    public List<Parameter> selectByPage(Integer pageNum) {
        return parameterMapper.selectByPage(pageNum);
    }
}
