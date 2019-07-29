package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Wallet;
import org.json.JSONArray;

import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/26 11:56
 */
public interface WalletService {
    int deleteByPrimaryKey(Integer walId);

    int insert(Wallet record);

    Wallet selectByPrimaryKey(Integer walId);

    List<Wallet> selectAll();

    int updateByPrimaryKey(Wallet record);

    int invest(JSONArray jsonArray);
}
