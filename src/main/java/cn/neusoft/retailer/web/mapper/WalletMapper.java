package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Wallet;
import java.util.List;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer walId);

    int insert(Wallet record);

    Wallet selectByPrimaryKey(Integer walId);

    List<Wallet> selectAll();

    int updateByPrimaryKey(Wallet record);
}