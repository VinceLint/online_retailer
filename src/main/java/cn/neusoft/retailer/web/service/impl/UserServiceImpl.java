package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/22 12:52
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer userId) throws Exception {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    public User selectByEnglishName(String englishName) {
        return selectByEnglishName(englishName);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public boolean insertByUserInfo(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean updateByName(User user) {
        return userMapper.updateByName(user);
    }

    @Override
    public boolean deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId) != 0;
    }

    public boolean insert(JSONObject jsonObject) {
        Integer id = jsonObject.getInt("id");
        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String mail = jsonObject.getString("mail");
        String phone = jsonObject.getString("phone");
        Integer wal_id = jsonObject.getInt("wal_id");
        Integer privilege = jsonObject.getInt("privilege");
        Integer type = jsonObject.getInt("type");
        String url = jsonObject.getString("url");
        String introduction = jsonObject.getString("introduction");
        String eng_name = jsonObject.getString("eng_name");

        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            if (id == user.getUserId()) {
                return false;
            }
        }

        User user = new User(id, password, name, mail, phone, wal_id, privilege, type, url, introduction, eng_name);
        //        插入成功返回真
        if (userMapper.insert(user)) return true;
        return false;
    }

    public boolean update(JSONArray jsonArray) {
//        修改前的信息
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject == null) return false;
//        实例化旧记录
        Integer id = jsonObject.getInt("id");
        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String mail = jsonObject.getString("mail");
        String phone = jsonObject.getString("phone");
        Integer wal_id = jsonObject.getInt("wal_id");
        Integer privilege = jsonObject.getInt("privilege");
        Integer type = jsonObject.getInt("type");
        String url = jsonObject.getString("url");
        String introduction = jsonObject.getString("introduction");
        String eng_name = jsonObject.getString("eng_name");
        User oldUser = new User(id, password, name, mail, phone, wal_id, privilege, type, url, introduction, eng_name);

        JSONObject newJsonObject = jsonArray.getJSONObject(1);
        if (newJsonObject == null) return false;

        User newUser = new User(
                newJsonObject.getInt("id"), newJsonObject.getString("password"),
                jsonObject.getString("name"),
                newJsonObject.getString("mail"),
                newJsonObject.getString("phone"),
                newJsonObject.getInt("wal_id"),
                newJsonObject.getInt("privilege"),
                newJsonObject.getInt("type"),
                newJsonObject.getString("url"),
                newJsonObject.getString("introduction"),
                newJsonObject.getString("eng_name")
        );

//        如果修改内容没变,则直接返回
        if (oldUser == null || newUser == null || oldUser.equals(newUser)) {
            return false;
        }

        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            if (id == user.getUserId()) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByDetail(JSONArray jsonArray) {
        if (jsonArray.length() == 0) return false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            userMapper.deleteByPrimaryKey(jsonObject.getInt("id"));
        }
        return true;
    }
    @Override
    public boolean updateByPrimaryKey_NoPassword(User user) {
        return userMapper.updateByPrimaryKey_NoPassword(user);
    }

}
