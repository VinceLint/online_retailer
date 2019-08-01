package cn.neusoft.retailer.web;

import cn.neusoft.retailer.web.mapper.BrandMapper;
import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BrandService;
import cn.neusoft.retailer.web.service.OrderService;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.MvoType;
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
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandMapper brandMapper;
    /**
     *@描述
     *@参数
     *@返回值
     *@创建人
     *@创建时间 2019/7/22 12:35
     *@修改人和其它信息
     */
    @org.junit.Test
    public void test() {
        User user = new User();
        user.setUserId(123);
        System.out.println(brandService.selectCountBrand(123));
        System.out.println(brandMapper.selectByPage(123, 0, 5));
        brandService.deleteByPrimaryKey(562);
//        for (int i = 0; i < 20; i ++){
//            Brand brand = new Brand();
//            brand.setBrandId(i+2000);
//            brand.setBrandMerId(123);
//            brandService.insert(brand);
//        }
    }

    /**
     *@描述 测试OrderService
     *@参数
     *@返回值
     *@创建人 胡献涛
     *@创建时间 2019/7/24 21:35
     *@修改人和其它信息
     */
    @org.junit.Test
    public void testOrderService() {
        orderService.selectByBrandUserId(1).forEach(map -> {
            System.out.println(map);
        });
    }

    /**
     *@描述  测试枚举类
     *@参数
     *@返回值
     *@创建人  林跃涛
     *@创建时间  2019/7/31 10:19
     *@修改人和其它信息
     */
    @org.junit.Test
    public void testEnum() {
        User user = new User();
        //获取枚举类的value的两种方法
        //1.通过枚举值
        System.out.println("'MvoType.其他'的枚举值为: "+MvoType.其他); //其他
        //2.通过枚举数组下标,默认从0开始
        System.out.println("MvoType第8个枚举值为: "+MvoType.values()[8]);  //其他
        //获取枚举值对应的code值(下标值),默认从0开始
        System.out.println("MvoType的枚举值'其他'对应的code值为: "+MvoType.其他.ordinal()); //8
    }
}
