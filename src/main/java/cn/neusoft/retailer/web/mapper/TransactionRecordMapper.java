package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
import java.util.List;

public interface TransactionRecordMapper {
    int deleteByPrimaryKey(String traRecId);

    int insert(TransactionRecord record);

    TransactionRecord selectByPrimaryKey(String traRecId);

    List<TransactionRecord> selectAll();

    int updateByPrimaryKey(TransactionRecord record);
}