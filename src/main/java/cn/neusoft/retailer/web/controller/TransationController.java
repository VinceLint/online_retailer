package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.TransationService;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.Result;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/30 22:37
 */
@Controller
public class TransationController {
    @Autowired
    private TransationService transationService;
    @Autowired
    private UserService userService;
    @RequestMapping("/toTransation")
    public String toTra(){
        return "html/bvo-transation.html";
    }

    @RequestMapping(value = "/toTransationList", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init( ) throws Exception{
        JSONArray jsonArray=null;

            List<TransactionRecord> transactionlist = transationService.getAll(1);

            jsonArray = JSONArray.fromObject(transactionlist);
//        System.out.println(jsonArray);


        return jsonArray;
    }

    @RequestMapping(value = "changeTransationList/{page}")
    @ResponseBody
    public JSONArray changeList(@PathVariable("page") String page,HttpServletRequest request){

//        System.out.println("page"+page);
        User user=new User();

                HttpSession session=request.getSession();
//        System.out.println(session);
         user = (User) session.getAttribute("user");

        //模拟session

//        user.setUserId(165108101);
//        user.setUserPassword("1177447a3e0364e44506716fca1659cd");
//        user.setUserName("zhihong");
//        user.setUserMail("893168374@qq.com");
//        user.setUserPhone("13437814328");
//        user.setUserPrivilege(0);

        Integer id=user.getUserId();
        Integer ty=user.getUserPrivilege();
        int p=Integer.valueOf(page);
        p-=1;


        JSONArray jsonArray= null;
        try {
            jsonArray = transationService.getAll(p,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonArray.add(ty);
        return jsonArray;
    }

    @RequestMapping(value = "changePage")
    @ResponseBody
    public Result changepage(HttpServletRequest request){

        User user=new User();

        HttpSession session=request.getSession();
//        System.out.println(session);
        user = (User) session.getAttribute("user");

        User user2= new User();
        try{
            user2=userService.selectByPrimaryKey(user.getUserId());
        }catch (Exception e){
            e.printStackTrace();
        }
        //模拟session

//        user.setUserId(165108101);
//        user.setUserPassword("1177447a3e0364e44506716fca1659cd");
//        user.setUserName("zhihong");
//        user.setUserMail("893168374@qq.com");
//        user.setUserPhone("13437814328");
//        user.setUserPrivilege(0);

        System.out.println(user.toString());
        if(user2.getUserWalId()==null){
            return new Result(false,"nowal");}else{
            return new Result(true,"havewal");
        }
    }

    @RequestMapping(value = "changeAllTransationList/{page}")
    @ResponseBody
    public JSONArray changeAllList(@PathVariable("page") String page,HttpServletRequest request){

        System.out.println("page"+page);
//        User user=new User();

//        HttpSession session=request.getSession();
//        System.out.println("流水单"+session);
//         user = (User) session.getAttribute("user");

        //模拟session

//        user.setUserId(165108101);
//        user.setUserPassword("1177447a3e0364e44506716fca1659cd");
//        user.setUserName("zhihong");
//        user.setUserMail("893168374@qq.com");
//        user.setUserPhone("13437814328");
//        user.setUserPrivilege(0);

//        Integer id=user.getUserId();
//        Integer ty=user.getUserPrivilege();
        int p=Integer.valueOf(page);
        p-=1;


        JSONArray jsonArray= null;
        try {
            jsonArray = transationService.getAll(p);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    @RequestMapping("/BrandTransation")
    public String toBrand(){return "html/brand-transation.html";}

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {


//        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
//        System.out.println(jsonArray);
//        JSONObject oldJSONObject = jsonArray.getJSONObject(0);
//        int id= oldJSONObject.getInt("tid");
//        String ttype=oldJSONObject.getString("ttype");
//        System.out.println("id+type"+id+ttype);

        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
//        String path = "/upload";

        File filePath = new File(path);
//        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
//            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date +name+ "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            System.out.println("路径"+targetFile.getPath());
            //将文件在服务器的存储路径返回
            return new Result(true,"/upload/" + fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new Result(false, "上传失败");
        }
    }

    @RequestMapping(value = "/ReviewForm",method= RequestMethod.POST,produces="application/json; charset:UTF-8")
    @ResponseBody
    public Result Review(@RequestBody String json){
        try{
            org.json.JSONArray jsonArray = new org.json.JSONArray(json);
            System.out.println(jsonArray);
            Result result = transationService.review(jsonArray);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
