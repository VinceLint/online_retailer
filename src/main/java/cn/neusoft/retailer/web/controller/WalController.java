package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.pojo.Wallet;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.service.WalletService;
import cn.neusoft.retailer.web.tools.Result;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/23 21:44
 */
@Controller
public class WalController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "tobvoWalletList")
    public String toWalletList(HttpServletRequest request){
//        HttpSession session=request.getSession();
//        User user = (User) session.getAttribute("user");
//        int type=user.getUserPrivilege();
// System.out.println(user.toString());
        return "html/bvo-WalletList.html";}


    @RequestMapping(value="/topAddWal")
    public String topAdd(HttpServletRequest request){
        User user=new User();
        //模拟session
//        user.setUserId(1);
//        user.setUserName("zhihong");
//        user.setUserPrivilege(0);
        HttpSession session=request.getSession();
//        System.out.println("注册跳转session"+session);
        user = (User) session.getAttribute("user");


//        System.out.println(user.toString());
        if(user.getUserWalId()==null){
            return "html/WalSignUp.html";}else{
            return "html/bvo-WalletList.html"   ;
        }}

    @RequestMapping(value = "/toSignWal2")
    @ResponseBody
    public Map<String, String> login(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("this");
        Map<String, String> result = new HashMap<>();
        org.json.JSONObject data = new org.json.JSONObject(json);
        String userName = (String) data.get("userName");
        String password =(String) data.get("userPassword");
        String email=(String) data.get("email");
//        System.out.println(data);
        //获取session
                User user=new User();
        HttpSession session=request.getSession();
//        System.out.println("注册session"+session);
         user = (User) session.getAttribute("user");
         User user2= new User();
         try{
             user2=userService.selectByPrimaryKey(user.getUserId());
         }catch (Exception e){
             e.printStackTrace();
         }
        //校验密码
        if (password.length()!=6) {
            result.put("INVALID_PASSWD", "Password Is Invalid");
            return result;

        }

        //校验用户信息
//        //模拟session
//        user.setUserId(165108101);
//        user.setUserPassword("1177447a3e0364e44506716fca1659cd");
//        user.setUserName("zhihong");
//        user.setUserMail("893168374@qq.com");
//        user.setUserPhone("13437814328");
//        user.setUserPrivilege(0);
        String Uusername=user.getUserName();
//        User user = (User) session.getAttribute("user");
            if(!Uusername.equals(userName)){
                 result.put("ERROR","username error");
                return result;}
            else if(!user.getUserMail().equals(email))
            {
                result.put("ERROR_MAIL","email error");
                return result;
            }

        else if (user == null) {
            result.put("INVALID_USERNAME", "No User");
            return result;
        }
        //创建钱包
        user2.setUserWalId(user.getUserId());
        Wallet wallet = new Wallet();
        wallet.setWalId(user.getUserId());
        wallet.setWalType(user.getUserPrivilege());
        wallet.setWalBalance((float) 0);
        wallet.setWalEmail(email);
        wallet.setWalPassword(password);
//        System.out.println(wallet.toString());
        try{
            walletService.insert(wallet);
            userService.updateByPrimaryKey(user2);
        }catch (Exception e){
            e.printStackTrace();
            result.put("ERROR_change",e.getMessage());
            return result;
        }




//        System.out.println(session);
        result.put("SUCCESS", "Have User");
        return result;
    }

    @RequestMapping("/toSignWal")
    public String toAddWal(Wallet wallet, Model model, HttpSession session){
        String pass=wallet.getWalPassword();
//        String mdpass= MD5.encrypt(pass);
//        wallet.setWalPassword(mdpass);
        model.addAttribute(wallet.toString());
        wallet.setWalType(1);
        wallet.setWalBalance((float) 0);
//        System.out.println(wallet.toString());

        walletService.insert(wallet);
        return "html/bvo-WalletList.html";
    }

    @GetMapping("/bvoWalletList")
    public String bvoWalletList(){
        return "bvo-WalletList";
    }

    @PostMapping("/toWalList")
    @ResponseBody
    public List<Wallet> toWalList(HttpServletResponse response, HttpServletRequest request){
//        System.out.println("ok");

        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        User user2= new User();
        try{
            user2=userService.selectByPrimaryKey(user.getUserId());
        }catch (Exception e){
            e.printStackTrace();
        }
        user=user2;
//        System.out.println("余额用户"+user.toString());
//        User user=new User();
        //模拟session
//        user.setUserId(165108101);
//        user.setUserPassword("1177447a3e0364e44506716fca1659cd");
//        user.setUserName("zhihong");
//        user.setUserMail("893168374@qq.com");
//        user.setUserPhone("13437814328");
//        user.setUserPrivilege(0);
//        user.setUserWalId(165108101);

        int i=user.getUserWalId();
        List<Wallet> list = new ArrayList<Wallet>();
//        System.out.println(i);
        Wallet wallet=new Wallet();
       try {
           wallet=walletService.selectByPrimaryKey(i);
           System.out.println(wallet.toString());
           list.add(wallet) ;
//        JSONObject jsonObject =new JSONObject();
//        JSONArray jsonArray = JSONArray.fromObject(list);
//           System.out.println(list.get(0).toString());
//        OutUtil.print(jsonArray, response);
       }catch (Exception e){
           e.printStackTrace();
       }

        return list;
    }

    @RequestMapping("/EditPassword")
    @ResponseBody
    public Result EditPw(@RequestBody String json,HttpServletRequest request){
        JSONArray jsonArray = new JSONArray(json);
//        System.out.println("pw+json"+jsonArray);

        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
//        System.out.println("pw+user"+user);
        JSONObject row=null;
        JSONObject pwObject=null;
        try {
             row = jsonArray.getJSONObject(0);
             pwObject = jsonArray.getJSONObject(1);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        int id = row.getInt("id");
        String opw = pwObject.getString("oldpw");
        String npw = pwObject.getString("newpw");
//        System.out.println("row+pw"+row+pwObject);

        if(user.getUserId()!=id){
            return new Result(false,"session_id_error");
        }
        Wallet wallet = new Wallet();
        try{
            wallet=walletService.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
        if(npw.length()!=6){
            return new Result(false,"password_format");
        }
//        System.out.println(wallet.getWalPassword()+opw);
        if(!wallet.getWalPassword().equals(opw)){
            return new Result(false,"password_error");
        }

        try{
            wallet.setWalPassword(npw);
            walletService.updateByPrimaryKey(wallet);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
        Result result = new Result(true,"success");
        return result;
    }
    @RequestMapping(value="/EditWal",method= RequestMethod.POST,produces="application/json; charset:UTF-8")
    @ResponseBody
    public Map<String, Object> EditWal(@RequestBody String json){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
//        System.out.println(jsonArray);
        int i=walletService.invest(jsonArray);
        if(i==7){
            resultmap.put("result","success");
        }
        else if(i==0) {
            resultmap.put("result","passError");

        }else if(i==3) {
            resultmap.put("result","unenough");
        }else if(i==4) {
            resultmap.put("result","recordError");
        }else
        {
                resultmap.put("result","error");
            }
//        System.out.println(resultmap);

        return resultmap;
    }
}

