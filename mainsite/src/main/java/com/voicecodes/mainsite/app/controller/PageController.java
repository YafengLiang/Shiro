package com.voicecodes.mainsite.app.controller;

import com.voicecodes.domain.model.User;
import com.voicecodes.domain.utils.EncryptAes256;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.voicecodes.domain.utils.EncryptAes256.Encrypt;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  15:09 2018/6/20
 */
@Controller
public class PageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = {"/","/main"})
    public String index(){
        return "main";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "redirect:login";
        }
        String password = null;
        try {
            password = EncryptAes256.EncodeHex(Encrypt(user.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("password::"+password +"  username::"+user.getUsername() );
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),password);
        Subject currentUser = SecurityUtils.getSubject();
        try{
            //在调用Login方法后，SecurityManager会收到AuthenticationToken，并将其大送给已配置的Realm执行必需的认证检查
            //每个Realm都能在必要时对提交的AuthenticationToken作出反应
            //所以这一步在调用login(Token)方法是，他会走到MyRealm.doGetAuthenticationInfo()方法中，具体验证方式如下：
            LOGGER.info("对用户【"+user.getUsername()+"】进行验证，验证开始");
            currentUser.login(token);
            LOGGER.info("对用户【"+user.getUsername()+"】进行验证，验证通过");
        }catch(UnknownAccountException uae){
            LOGGER.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            LOGGER.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch (DisabledAccountException sae){
            LOGGER.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,帐号已经禁止登录");
            redirectAttributes.addFlashAttribute("message", "帐号已禁用");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            LOGGER.info("对用户[" + user.getUsername() + "]进行登录验证..验证未通过,堆栈轨迹如下");
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        if (currentUser.isAuthenticated()){
            LOGGER.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            //把当前用户放入session
            Session session = currentUser.getSession();
            session.setAttribute("currentUser",user);
//            Auditrail auditrail = new Auditrail();
//            auditrail.setLoginid(user.getUsername());
//            auditrail.setTimestamp(new Date());
//            auditrail.setType("user");
//            auditrail.setDescription(user.getUsername()+" USER_LOGIN.SUCCESS");
//            auditrail.setAction("LOGIN_ACTION");
//            auditrail.setParameters(user.getUsername());
//            auditrailService.addAuditrail(auditrail);

//             map.put("msg",msg);
            return "main";
        }else {
//            Auditrail auditrail = new Auditrail();
//            String name = SecurityContextProvider.class.getName();
//            auditrail.setLoginid(user.getUsername());
//            auditrail.setTimestamp(new Date());
//            auditrail.setType("user");
//            auditrail.setDescription(user.getUsername()+" USER_LOGOFF.SUCCESS");
//            auditrail.setAction("LOGOFF_ACTION");
//            auditrail.setParameters(name);
//            auditrailService.addAuditrail(auditrail);
            token.clear();
            return "redirect:login";
        }
    }

//    @RequestMapping(value = "/logout")
//    public String logout(){
//        Logs.LOGGER.info("=================================");
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        Auditrail auditrail = new Auditrail();
//        auditrail.setLoginid(user.getUsername());
//        auditrail.setTimestamp(new Date());
//        auditrail.setType("user");
//        auditrail.setDescription(user.getUsername()+" LOGOUT.SUCCESS");
//        auditrail.setAction("LOGOUT_ACTION");
//        auditrail.setParameters(user.getUsername());
//        auditrailService.addAuditrail(auditrail);
//        return "login";
//    }

    @RequestMapping(value = "toUser")
    public String toUser(){
        return "user/ViewUser";
    }
}
