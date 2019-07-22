package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Company;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-22 16:25
 */
public interface CompanyAdminService {
    Company selectByCompanyId(Integer companyId);

    Company selectByCompanyName(String string);

    boolean deleteByCompanyId(Integer companyId);

    boolean updateByCompanyId(Company company);

    boolean insertByCompanyId(Company company);
}
