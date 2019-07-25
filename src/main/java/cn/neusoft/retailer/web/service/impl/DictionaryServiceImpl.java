package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.DictionaryMapper;
import cn.neusoft.retailer.web.pojo.Dictionary;
import cn.neusoft.retailer.web.service.DictionaryService;
import org.json.JSONObject;
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

    /**
     *@描述  插入一个字典,并根据该字典类型,判断是否能插入
     *@参数  JSONObject对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/24 20:14
     *@修改人和其它信息
     */
    @Override
    public boolean insert(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        String describe = jsonObject.getString("describe");
        Integer code = jsonObject.getInt("code");
        String cvalue = jsonObject.getString("cvalue");
//        读出字典类型相同的字典
        List<Dictionary> dictionaryList = dictionaryMapper.selectAll();
        for (Dictionary dictionary: dictionaryList){
//            如果该类型字典已经有相应的编码或者编码值,则插入失败
            if (dictionary.getDicCode()==code||dictionary.getDicValue().equals(cvalue)){
                return false;
            }
        }
        Dictionary dictionary = new Dictionary(null, type, describe, code, cvalue);
//        插入成功返回真
        if (dictionaryMapper.insert(dictionary)==1) return true;
        return false;
    }

    @Override
    public Dictionary selectByPrimaryKey(Integer dicId) {
        return null;
    }

    @Override
    public List<Dictionary> selectAll() {
        return dictionaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        return 0;
    }
}
