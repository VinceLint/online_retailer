package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.ParameterMapper;
import cn.neusoft.retailer.web.pojo.Parameter;
import cn.neusoft.retailer.web.service.ParameterService;
import org.json.JSONArray;
import org.json.JSONObject;
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
     * @描述 根据页号来查询对应的记录
     * @参数 整型对象
     * @返回值 参数列表对象
     * @创建人 林跃涛
     * @创建时间 2019/7/29 15:24
     * @修改人和其它信息
     */
    @Override
    public List<Parameter> selectByPage(Integer pageNum) {
        List<Parameter> parameters = parameterMapper.selectByPage(pageNum);
        return parameters;
    }

    /**
     *@描述  查找所有记录
     *@参数  无
     *@返回值  参数列表数组
     *@创建人  林跃涛
     *@创建时间  2019/7/30 11:38
     *@修改人和其它信息
     */
    @Override
    public List<Parameter> selectAll() {
        return parameterMapper.selectAll();
    }

    /**
     *@描述  更新对象
     *@参数  JSONObject对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/30 22:02
     *@修改人和其它信息
     */
    @Override
    public boolean updateByPrimaryKey(JSONObject jsonObject) {
        Parameter parameter = new Parameter(jsonObject.getString("parameterKey"),
                jsonObject.getInt("parameterValue"),jsonObject.getString("parameterDescribe"));
        if(parameterMapper.updateByPrimaryKey(parameter)==1){
            return true;
        }
        return false;
    }

    /**
     *@描述  插入一条新记录
     *@参数  JSONObject对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/30 22:50
     *@修改人和其它信息
     */
    @Override
    public boolean insert(JSONObject jsonObject) {
        Parameter parameter = new Parameter(jsonObject.getString("parameterKey"),
                jsonObject.getInt("parameterValue"),jsonObject.getString("parameterDescribe"));
        List<Parameter> parameters = parameterMapper.selectAll();
        for (Parameter p : parameters){
            if(p.getParId().equals(jsonObject.getString("parameterKey")))
            return false;
        }
        if(parameterMapper.insert(parameter)==1){
            System.out.println("修改了");
            return true;
        }
        System.out.println("没修改");
        return false;
    }

    /**
     *@描述  删除选中的记录
     *@参数  JSONObject对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/30 23:35
     *@修改人和其它信息
     */
    @Override
    public boolean delete(JSONArray jsonArray) {
        if (jsonArray.length()==0) return false;
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("parameterKey"));
            parameterMapper.deleteByPrimaryKey(jsonObject.getString("parameterKey"));
        }
        return true;
    }

}
