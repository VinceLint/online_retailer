package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.WalletMapper;
import cn.neusoft.retailer.web.pojo.Wallet;
import cn.neusoft.retailer.web.service.WalletService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/26 11:57
 */
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public int deleteByPrimaryKey(Integer walId) {
        int i = walletMapper.deleteByPrimaryKey(walId);
        return i;
    }

    @Override
    public int insert(Wallet record) {
        int i = walletMapper.insert(record);
        return i;
    }

    @Override
    public Wallet selectByPrimaryKey(Integer walId) {
        Wallet wallet = walletMapper.selectByPrimaryKey(walId);
        return null;
    }

    @Override
    public List<Wallet> selectAll() {
        List<Wallet> list = walletMapper.selectAll();

        return list;
    }

    @Override
    public int updateByPrimaryKey(Wallet record) {
        int i = walletMapper.updateByPrimaryKey(record);
        return i;
    }

    @Override
    public int invest(JSONArray jsonArray) {
        JSONObject oldJSONObject = jsonArray.getJSONObject(0);
        JSONObject editform = jsonArray.getJSONObject(1);

        int id = oldJSONObject.getInt("id");
        float bal = oldJSONObject.getFloat("bal");
        int sign = oldJSONObject.getInt("sign");

        String pass = editform.getString("pass");
        float money = editform.getFloat("money");


        System.out.println("id" + id + "bal" + bal + "pass" + pass + "money" + money + "sign" + sign);
        //修改钱包
        Wallet oldWallet = walletMapper.selectByPrimaryKey(id);
        float balence = oldWallet.getWalBalance();
        System.out.println(oldWallet + "旧钱包");

        if (!oldWallet.getWalPassword().equals(pass)) {
            System.out.println("密码错误");
            return 0;

        } else if (balence != bal) {
            System.out.println("数据错误");
            return 1;
        } else if (sign == 1) {
            oldWallet.setWalBalance(balence + money);
        } else if (sign == -1) {
            if (balence < money) {
                System.out.println("余额不足");
                return 3;
            } else {
                oldWallet.setWalBalance(balence - money);
            }
        }
        //修改数据库相关
        int i = walletMapper.updateByPrimaryKey(oldWallet);
        System.out.println("修改结果" + i);

        if (i > 0) {
            System.out.println("修改成功");
            return 7;
        } else {
            System.out.println("修改错误");
            return 2;
        }
    }
}
