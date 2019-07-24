package cn.neusoft.retailer.web;

import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/19 11:08
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:springmvc.xml"})

public class Test {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    /**
     *@描述
     *@参数
     *@返回值
     *@创建人
     *@创建时间  2019/7/22 12:35
     *@修改人和其它信息
     */
    @org.junit.Test
    public void test(){
        User user = new User();
        user.setUserId(123);
        userMapper.insert(user);
    }

}
