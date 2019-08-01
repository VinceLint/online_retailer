package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
import java.util.List;

public interface TransactionRecordMapper {
    int deleteByPrimaryKey(Integer traRecId);

    int insert(TransactionRecord record);

    TransactionRecord selectByPrimaryKey(Integer traRecId);

    List<TransactionRecord> selectAll();

    int updateByPrimaryKey(TransactionRecord record);
}