package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Dictionary;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/24 10:41
 */
public interface DictionaryService {
    int deleteByPrimaryKey(Integer dicId);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Integer dicId);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);
}
