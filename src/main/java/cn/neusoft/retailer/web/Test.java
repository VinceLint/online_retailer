package cn.neusoft.retailer.web;

import cn.neusoft.retailer.web.mapper.BrandMapper;
import cn.neusoft.retailer.web.mapper.DictionaryMapper;
import cn.neusoft.retailer.web.mapper.UserMapper;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BrandOrderService;
import cn.neusoft.retailer.web.service.BrandService;
import cn.neusoft.retailer.web.service.DictionaryService;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.ClearRedies;
import cn.neusoft.retailer.web.tools.FtpUtils;
import cn.neusoft.retailer.web.tools.MvoType;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.UUID;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/19 11:08
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:springmvc.xml"})

public class Test {
    @Autowired
    private BrandOrderService brandOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryMapper dictionaryMapper;

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
//        brandService.deleteByPrimaryKey(562);
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
        brandOrderService.selectByBrandUserId(1).forEach(map -> {
            System.out.println(map);
        });
    }

    /**
     * @描述 测试枚举类
     * @参数
     * @返回值
     * @创建人 林跃涛
     * @创建时间 2019/7/31 10:19
     * @修改人和其它信息
     */
    @org.junit.Test
    public void testEnum() {
        User user = new User();
        //获取枚举类的value的两种方法
        //1.通过枚举值
        System.out.println("'MvoType.其他'的枚举值为: " + MvoType.其他); //其他
        //2.通过枚举数组下标,默认从0开始
        System.out.println("MvoType第8个枚举值为: " + MvoType.values()[8]);  //其他
        //获取枚举值对应的code值(下标值),默认从0开始
        System.out.println("MvoType的枚举值'其他'对应的code值为: " + MvoType.其他.ordinal()); //8
    }


    @org.junit.Test
    public void testMysql() {
//        List<Dictionary> dictionarys= dictionaryMapper.selectByType("USER_PRIVILEGE");
//        System.out.println(dictionarys);
//        Order order=new Order();
//        brandOrderService.insert(order);
        /*List<Order> orders=brandOrderService.selectAll();
        System.out.println(orders.get(0).getOrderId());*/
        String string = UUID.randomUUID().toString();
        System.out.println(string);
    }

    @org.junit.Test
    public void testFtp2() throws Exception{
        String str = "G:\\林跃涛\\新建文件夹\\test.txt";
        FtpUtils ftpUtils = new FtpUtils();
        System.out.println(ftpUtils.uploadFile("/usr/local/nginx/html/online_retailer/upload","4567.txt",str));
//        /usr/local/nginx/html/online_retailer/upload
    }

    @org.junit.Test
    public void Test3() throws Exception{
        //创建要提个FTPClient对像
        FTPClient client = new FTPClient();
        //创建FTP链接
        client.connect("47.107.168.99", 21);
        //登录FTP服务器：用户名密码
        client.login("root", "lyt970611*");
        //上传:服务端文件名，上传文件的InputStream
        //读取文件
        client.enterLocalPassiveMode();
        FileInputStream in = new FileInputStream(new File("G:\\林跃涛\\新建文件夹\\做一道菜.jpg"));
        //设置上传路径,远程站点路径
        boolean falg = client.changeWorkingDirectory("/usr/local/nginx/html/online_retailer/upload");
        //修改上传文件的格式，ftp默认传的文本，而图片是二进制
        client.setFileType(FTP.BINARY_FILE_TYPE);
        System.out.println(client.storeFile("hello.jpg", in));
        //关闭链接

    }

    @org.junit.Test
    public void Test4() throws Exception{
        FtpUtils ftpUtils = new FtpUtils();

        ftpUtils.deleteFile("/","做一道菜1.jpg");

    }
    /**
     * @描述 手动清除缓存类
     * @参数
     * @返回值
     * @创建人 庄志宏
     * @创建时间 2019/8/8 10:19
     * @修改人和其它信息
     */
    @org.junit.Test
    public void Test(){
        ClearRedies clearRedies = new ClearRedies();
        clearRedies.clearAll();

    }
}
