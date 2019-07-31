package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Parameter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/29 15:16
 */
public interface ParameterService {
    List<Parameter> selectByPage(Integer pageNum);

    List<Parameter> selectAll();

    boolean updateByPrimaryKey(JSONObject jsonObject);

    boolean insert(JSONObject jsonObject);

    boolean delete(JSONArray jsonArray);
}
