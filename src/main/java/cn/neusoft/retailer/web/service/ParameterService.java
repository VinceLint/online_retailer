package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Parameter;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/29 15:16
 */
public interface ParameterService {
    List<Parameter> selectByPage(Integer pageNum);
}
