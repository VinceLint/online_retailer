package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);
}