package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Company;
import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(Company record);

    Company selectByPrimaryKey(Integer companyId);

    List<Company> selectAll();

    int updateByPrimaryKey(Company record);
}