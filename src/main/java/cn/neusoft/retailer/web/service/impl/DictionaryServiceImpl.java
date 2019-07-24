package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.DictionaryMapper;
import cn.neusoft.retailer.web.pojo.Dictionary;
import cn.neusoft.retailer.web.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/24 10:42
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public int deleteByPrimaryKey(Integer dicId) {
        return 0;
    }

    @Override
    public int insert(Dictionary record) {
        return dictionaryMapper.insert(record);
    }

    @Override
    public Dictionary selectByPrimaryKey(Integer dicId) {
        return null;
    }

    @Override
    public List<Dictionary> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        return 0;
    }
}
