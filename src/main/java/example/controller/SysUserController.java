package example.controller;

import example.pojo.SysUser;
import example.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/sysUserController")
@Slf4j
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/showUserToJspById/{userId}")
    public String showUser(Model model, @PathVariable("userId") Long userId)
    {
        SysUser user = this.sysUserService.getById(userId);
        model.addAttribute("user", user);
//      调用出错，暂时没搞定，报Ehcache初始化失败
//      EhcacheManager.get("123");
        return "showUser";
    }

    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId)
    {
        //计算请求时间，面试中问到过，这是最简单的方法。第二种方法用AOP实现。
        long startTime = System.currentTimeMillis();
        SysUser user = sysUserService.getById(userId);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        return user;
    }

    @RequestMapping("/test-logback")
    @ResponseBody
    public Date testLogback()
    {
        log.trace("-----------------------------------trace");
        log.debug("-----------------------------------debug");
        log.info("-----------------------------------info");
        log.warn("-----------------------------------warn");
        log.error("-----------------------------------error");
        return new Date();
    }


}
