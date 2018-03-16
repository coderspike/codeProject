package example.controller;

import example.pojo.SysUser;
import example.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/showUserToJspById/{userId}")
    public String showUser(Model model, @PathVariable("userId") Long userId) {
        SysUser user = this.sysUserService.getById(userId);
        model.addAttribute("user", user);
//        调用出错，暂时没搞定，报Ehcache初始化失败
//        EhcacheManager.get("123");
        return "showUser";
    }

    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        return user;
    }


    @RequestMapping("/test-logback")
    @ResponseBody
    public Date testLogback() {
        LOG.trace("-----------------------------------trace");
        LOG.debug("-----------------------------------debug");
        LOG.info("-----------------------------------info");
        LOG.warn("-----------------------------------warn");
        LOG.error("-----------------------------------error");
        return new Date();
    }


}
