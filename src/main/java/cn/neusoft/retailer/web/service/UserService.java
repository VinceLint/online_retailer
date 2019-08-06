package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/22 12:52
 */
public interface UserService {
    User selectByPrimaryKey(Integer userId) throws Exception;
    User selectByName(String userName);
    User selectByEnglishName(String englishName);
    List<User> selectAll();
    boolean insertByUserInfo(User user);
    boolean updateByPrimaryKey(User user);

    boolean updateByName(User user);
    boolean deleteByPrimaryKey(Integer userId);

    boolean insert(JSONObject jsonObject);
    boolean update(JSONArray jsonArray);
    boolean deleteByDetail(JSONArray jsonArray);
    boolean updateByPrimaryKey_NoPassword(User user);

}
