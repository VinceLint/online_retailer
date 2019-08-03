package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.MenuMapper;
import cn.neusoft.retailer.web.pojo.Menu;
import cn.neusoft.retailer.web.service.MenuService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邹文深
 * @version 1.1
 * @date 2019/8/1 23:10
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int insert(Menu record) {
        List<Menu> menuList=menuMapper.selectAll();
        if(menuList.contains(record)){
            return 0;
        }
        menuMapper.insert(record);
        return 1;
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean insert(JSONObject jsonObject) {
        Menu menu=new Menu(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("url"),
                jsonObject.getInt("privilege")
        );
        List<Menu> menuList=menuMapper.selectAll();
        if(menuList.contains(menu)){
            return false;
        }
        if(menuMapper.insert(menu)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(JSONArray jsonArray) {
        JSONObject oldJsonObject = jsonArray.getJSONObject(0);
        JSONObject newJsonObject = jsonArray.getJSONObject(1);
        if (newJsonObject==null||oldJsonObject==null) return false;

        Menu oldMenu=new Menu(oldJsonObject.getInt("id"),oldJsonObject.getString("name"),oldJsonObject.getString("url"),oldJsonObject.getInt("privilege"));
        Menu newMenu=new Menu(newJsonObject.getInt("id"),newJsonObject.getString("name"),newJsonObject.getString("url"),newJsonObject.getInt("privilege"));


//        如果修改内容没变,则直接返回
        if(oldMenu==null||newMenu==null||newMenu.equals(oldMenu)){
            return false;
        }
        updateByPrimaryKey(newMenu);
        return true;
    }

    //批量删除
    @Override
    public boolean deleteByDetail(JSONArray jsonArray) {
        if (jsonArray.length() == 0) return false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            menuMapper.deleteByPrimaryKey(jsonObject.getInt("id"));
        }
        return true;
    }
}
