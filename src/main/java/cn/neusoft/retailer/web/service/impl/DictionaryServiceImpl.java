package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.DictionaryMapper;
import cn.neusoft.retailer.web.pojo.Dictionary;
import cn.neusoft.retailer.web.service.DictionaryService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
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
        System.out.println(jsonObject);
//        读出字典类型相同的字典
        List<Dictionary> dictionaryList = dictionaryMapper.selectByType(type);
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

    /**
     *@描述  修改单个字典,用旧记录和新记录才能完成
     * 第一步:如果新旧记录完全相同,则无需修改,直接返回false
     * 第二步:拿到旧记录对应数据库的记录(因为id唯一,其他字段不唯一,直接修改需要id值)
     * 第三步:如果要修改的新记录和数据库中的记录(相同的type,除去旧记录)出现编码值或编码重复,修改失败
     *@参数  JSONArray对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/25 11:51
     *@修改人和其它信息
     */
    @Override
    public boolean update(JSONArray jsonArray){
//        修改前的信息
        JSONObject oldJsonObject = jsonArray.getJSONObject(0);
//        实例化旧记录
        Dictionary oldDictionary = new Dictionary(oldJsonObject.getString("type"),
                oldJsonObject.getString("describe"), oldJsonObject.getInt("code"),
                oldJsonObject.getString("cvalue"));

//        修改后的信息
        JSONObject newJsonObject = jsonArray.getJSONObject(1);
//        实例化新记录
        Dictionary newDictionary = new Dictionary(newJsonObject.getString("type"),
                newJsonObject.getString("describe"), newJsonObject.getInt("code"),
                newJsonObject.getString("cvalue"));

//        如果修改内容没变,则直接返回
        if(oldDictionary.getDicType().equals(newDictionary.getDicType())
            &&oldDictionary.getDicDiscribe().equals(newDictionary.getDicDiscribe())
            &&oldDictionary.getDicCode().equals(newDictionary.getDicValue())
            &&oldDictionary.getDicValue().equals(newDictionary.getDicValue())){
            return false;
        }

//        判断字典能否修改(即与type字段对应的记录的code值和value值不能冲突)
        List<Dictionary> dictionaryList = dictionaryMapper.selectByType(newDictionary.getDicType());
//        遍历相同type的数组,判断新记录和数组中(除去旧记录)是否出现重复
        for (Dictionary dictionary: dictionaryList){
//            如果该类型字典已经有相应的编码或者编码值,则修改失败
//            先找到旧记录的id值先
            if (dictionary.getDicCode()==oldDictionary.getDicCode()
                &&dictionary.getDicValue().equals(oldDictionary.getDicValue())){
//                把旧记录的id值赋值给新旧记录,方便插入
                oldDictionary.setDicId(dictionary.getDicId());
                newDictionary.setDicId(dictionary.getDicId());
                System.out.println(dictionary.getDicId());
            }
//            如果不是旧记录的话,则需要判断是否出现相同的编码或者编码值
            else{
//                出现重复,更新失败
                if (dictionary.getDicCode()==newDictionary.getDicCode()
                    ||dictionary.getDicValue().equals(newDictionary.getDicValue())){
                    return false;
                }
            }
//
        }
        System.out.println(newDictionary.getDicId()+" "+newDictionary.getDicCode());
        //没有冲突,可以修改,开始更新数据库
        //更新成功
        if (dictionaryMapper.updateByPrimaryKey(newDictionary)==1) return true;
//        更新失败
        System.out.println("更新到数据库失败");
        return false;
    }

    /**
     *@描述  删除勾选中的JSON数组
     *@参数  JSONArray对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/7/26 10:32
     *@修改人和其它信息
     */
    @Override
    public boolean deleteByDetail(JSONArray jsonArray) {
        if (jsonArray.length()==0) return false;
        Dictionary[] dictionaries = new Dictionary[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            dictionaries[i] = new Dictionary(jsonObject.getString("type"),
                    jsonObject.getString("describe"),jsonObject.getInt("code"),
                        jsonObject.getString("cvalue"));
        }

        for (int i =0;i<dictionaries.length;i++){
            dictionaryMapper.deleteByDetail(dictionaries[i]);
        }
        return true;
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
