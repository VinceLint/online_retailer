package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.TransactionRecordMapper;
import cn.neusoft.retailer.web.pojo.TransactionRecord;
import cn.neusoft.retailer.web.service.TransationService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/31 9:16
 */
@Service
public class TransationServiceImpl implements TransationService {

    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer traRecId) {
        return 0;
    }

    @Override
    public int insert(TransactionRecord record) {
        return 0;
    }

    @Override
    public TransactionRecord selectByPrimaryKey(Integer traRecId) {
        return null;
    }

    @Override
    public List<TransactionRecord> selectAll() {
        return null;
    }

    @Override
    public JSONArray getAll(int p) {
        List<TransactionRecord> transactionlist = transactionRecordMapper.selectAll();
        for (TransactionRecord t : transactionlist) {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss").format(t.getTraRecDate());
            t.setTraRecDateStr(dateStr);
//            System.out.println(dateStr);
        }
        List<TransactionRecord> outList =new ArrayList<TransactionRecord>();

        TransactionRecord transactionRecord =null;
        for(int i=0;i<7;i++){
            if((i+7*p)>=transactionlist.size()){break;}
                outList.add(transactionlist.get(7*p+i));

        }
        int total = transactionlist.size();
        System.out.println("total"+total);
        System.out.println("outlist"+outList);
        JSONArray jsonArray = JSONArray.fromObject(outList);
        jsonArray.add(total);

        System.out.println(jsonArray);


        return jsonArray;
    }

    @Override
    public int updateByPrimaryKey(TransactionRecord record) {
        return 0;
    }
}
