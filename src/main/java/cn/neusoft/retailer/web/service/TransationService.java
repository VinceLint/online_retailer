package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
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

    JSONArray getAll(int p);


    int updateByPrimaryKey(TransactionRecord record);
}
