package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Menu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 邹文深
 * @version 1.1
 * @date 2019/8/1 23:04
 */
public interface MenuService {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    boolean insert(JSONObject jsonObject);
    boolean update(JSONArray jsonArray);
    boolean deleteByDetail(JSONArray jsonArray);
}
