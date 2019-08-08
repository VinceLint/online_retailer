package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.TransactionRecordMapper;
import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.mapper.WalletMapper;
import cn.neusoft.retailer.web.pojo.TransactionRecord;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.pojo.Wallet;
import cn.neusoft.retailer.web.service.TransationService;
import cn.neusoft.retailer.web.tools.Result;
import net.sf.json.JSONArray;
import org.json.JSONObject;
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

    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private UserMapper userMapper;

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
    public JSONArray getAll(int p,int id) {
        List<TransactionRecord> transactionlist = transactionRecordMapper.selectAll();
        List<TransactionRecord> userTraList=new ArrayList<TransactionRecord>();
        for (TransactionRecord t : transactionlist) {
            if(t.getTraRecWalId()==id){
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss").format(t.getTraRecDate());
                t.setTraRecDateStr(dateStr);
                userTraList.add(t);
            }

//            System.out.println(dateStr);
        }
        List<TransactionRecord> outList =new ArrayList<TransactionRecord>();

        TransactionRecord transactionRecord =null;
        for(int i=0;i<7;i++){
            if((i+7*p)>=userTraList.size()){break;}

                outList.add(userTraList.get(7*p+i));

        }
        int total = userTraList.size();
        System.out.println("total"+total);
        System.out.println("outlist"+outList);
        JSONArray jsonArray = JSONArray.fromObject(outList);
        jsonArray.add(total);

        System.out.println(jsonArray);


        return jsonArray;
    }

    @Override
    public JSONArray getAll(int p) {
        List<TransactionRecord> transactionlist = transactionRecordMapper.selectAll();
        List<TransactionRecord> toTraList =new ArrayList<TransactionRecord>();
        List<User> userList = new ArrayList<User>();
        Integer WalId=null;
        User user=null;
        String UserName=null;
        Integer userid=null;
//        try{
            userList=userMapper.selectAll();
//        }catch (Exception e){e.printStackTrace();}
//        System.out.println(userList);
        for (TransactionRecord t : transactionlist) {
            if(t.getTraRecStatus()==0){
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm;ss").format(t.getTraRecDate());

                t.setTraRecDateStr(dateStr);
                //插入用户名
                WalId=t.getTraRecWalId();
//                System.out.println(userList);
//                System.out.println(WalId);
                for(User u:userList){
//                    System.out.println(u.getUserName());
//                    System.out.println(u.getUserId());
                    userid=u.getUserId();
                    if(userid.equals(WalId)){
                        t.setTraRecUserName(u.getUserName());
//                        System.out.println(t.getTraRecUserName());
                    }
                }

                toTraList.add(t);

            }

        }
        System.out.println(toTraList);

        List<TransactionRecord> outList =new ArrayList<TransactionRecord>();

        TransactionRecord transactionRecord =null;
        for(int i=0;i<7;i++){
            if((i+7*p)>=toTraList.size()){break;}

            outList.add(toTraList.get(7*p+i));

        }
        int total = toTraList.size();
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

    @Override
    public Result review(org.json.JSONArray json) {
        System.out.println(json);
        //获取json
        JSONObject formObject = json.getJSONObject(0);
        JSONObject reviewObject = json.getJSONObject(1);
       int traid= formObject.getInt("traid");
       int id=formObject.getInt("tid");
       String choose=reviewObject.getString("resource");
       float num=formObject.getFloat("tsum");
       float money=formObject.getFloat("tmoney");
       String type=formObject.getString("ttype");
       String filepath=reviewObject.getString("fsrc");
       String reason =reviewObject.getString("desc");
        System.out.println("traid"+traid+"type"+type+"filepath"+filepath+"resource"+choose+"reason"+reason);
//填入水单地址
        TransactionRecord transactionRecord=null;
        transactionRecord=transactionRecordMapper.selectByPrimaryKey(traid);
        transactionRecord.setTraRecExchangememo(filepath);

        Wallet wallet = null;
        wallet=walletMapper.selectByPrimaryKey(id);
        if(choose.equals("通过")){
            transactionRecord.setTraRecStatus(1);
            transactionRecordMapper.updateByPrimaryKey(transactionRecord);
            System.out.println("修改状态1");
            return new Result(true,"修改为通过");
        }else{
            transactionRecord.setTraRecStatus(2);
            transactionRecord.setTraRecExchangememo(reason);
            transactionRecord.setTraRecExchangememo(filepath);
            transactionRecordMapper.updateByPrimaryKey(transactionRecord);
            System.out.println("修改状态2");
            switch (type){
            case "提现":
                wallet.setWalBalance(wallet.getWalBalance()+num);
                walletMapper.updateByPrimaryKey(wallet);
                break;
                case "充值":
                    wallet.setWalBalance(wallet.getWalBalance()-num);
                    walletMapper.updateByPrimaryKey(wallet);
                    break;
                default : System.out.println(" default ");
            }
            System.out.println("修改余额");


            return new Result(true,"修改为不通过");
        }
    }
}
