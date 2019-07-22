package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.CompanyMapper;
import cn.neusoft.retailer.web.pojo.Company;
import cn.neusoft.retailer.web.service.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-22 20:55
 */
@Service("CompanyAdminService")
public class CompanyAdminServiceImpl implements CompanyAdminService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public Company selectByCompanyId(Integer companyId) {
        return companyMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public Company selectByCompanyName(String string) {
        return companyMapper.selectByCompanyName(string);
    }

    @Override
    public boolean deleteByCompanyId(Integer companyId) {
        return companyMapper.deleteByPrimaryKey(companyId) != 0;
    }

    @Override
    public boolean updateByCompanyId(Company company) {
        return companyMapper.updateByPrimaryKey(company) != 0;
    }

    @Override
    public boolean insertByCompanyId(Company company) {
        return companyMapper.insert(company) != 0;
    }
}
