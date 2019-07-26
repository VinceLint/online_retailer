package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Dictionary;
import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer dicId);
    int deleteByDetail(Dictionary dictionary);
    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Integer dicId);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);

    List<Dictionary> selectByType(String type);
}