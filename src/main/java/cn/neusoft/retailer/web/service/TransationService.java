package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
import cn.neusoft.retailer.web.tools.Result;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/31 9:15
 */
public interface TransationService {
    int deleteByPrimaryKey(Integer traRecId);

    int insert(TransactionRecord record);

    TransactionRecord selectByPrimaryKey(Integer traRecId);

    List<TransactionRecord> selectAll();

    JSONArray getAll(int p,int id);

    JSONArray getAllList(int p);

    int updateByPrimaryKey(TransactionRecord record);

    Result review(org.json.JSONArray json);
}
