package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/28 15:44
 */
@Controller
@RequestMapping(value = "/mail")
public class SendMailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    @RequestMapping("/send/{userName}")
    @ResponseBody
    public String sendMail(@PathVariable("userName") String userName) {

        String code = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        User user = null;
        try {
            user = userService.selectByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String mail = user.getUserMail();

        MimeMessage mMessage = javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            from = prop.get("mail.smtp.username") + "";
            mMessageHelper = new MimeMessageHelper(mMessage, true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo(mail);//收件人邮箱
            mMessageHelper.setSubject("重置密码");//邮件的主题
            mMessageHelper.setText("尊敬的" + userName + "用户您好，本次操作的验证码为：" + code);
            //邮件的文本内容，true表示文本以html格式打开
            //File file = new File("");//在邮件中添加文件
            //FileSystemResource resource = new FileSystemResource(file);
            //mMessageHelper.addInline("", resource);//这里指定一个id,在上面引用
            //mMessageHelper.addAttachment("", resource);//在邮件中添加一个附件
            javaMailSender.send(mMessage);//发送邮件
        } catch (MessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return code;
    }
}
