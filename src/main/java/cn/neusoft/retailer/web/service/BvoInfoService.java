package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.User;
import org.json.JSONObject;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 8:37
 */
public interface BvoInfoService {
    User selectByPrimaryKey(Integer userId);

    boolean update(JSONObject jsonObject, Integer userId);
}
