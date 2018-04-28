package example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
//    @Resource
//    private AmqpTemplate amqpTemplate; 暂时不启用rabbitmq

    @RequestMapping("/index.jhtml")
    public ModelAndView getIndex(HttpServletRequest request) throws Exception
    {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/exceptionForPageJumps.jhtml")
    public ModelAndView exceptionForPageJumps(HttpServletRequest request) throws Exception
    {
        throw new Exception("空");
    }

    @RequestMapping(value = "/businessException.json", method = RequestMethod.POST)
    @ResponseBody
    public String businessException(HttpServletRequest request) throws Exception
    {
        throw new Exception("空");
    }

    @RequestMapping(value = "/otherException.json", method = RequestMethod.POST)
    @ResponseBody
    public String otherException(HttpServletRequest request) throws Exception
    {
        throw new Exception();
    }

    //跳转到登录页面
    @RequestMapping("/login.jhtml")
    public ModelAndView login() throws Exception
    {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    //跳转到登录成功页面
    @RequestMapping("/loginsuccess.jhtml")
    public ModelAndView loginsuccess() throws Exception
    {
        ModelAndView mav = new ModelAndView("loginsuccess");
        return mav;
    }

    @RequestMapping("/newPage.jhtml")
    public ModelAndView newPage() throws Exception
    {
        ModelAndView mav = new ModelAndView("newPage");
        return mav;
    }

    @RequestMapping("/newPageNotAdd.jhtml")
    public ModelAndView newPageNotAdd() throws Exception
    {
        ModelAndView mav = new ModelAndView("newPageNotAdd");
        return mav;
    }

    /**
     * 验证用户名和密码
     *
     * @return
     */
    @RequestMapping(value = "/checkLogin.json", method = RequestMethod.POST)
    @ResponseBody
    public String checkLogin(String username, String password) throws Exception
    {
        System.out.println("调用shiro 验证");
        this.testJedis();//测试redis
        //rabbitMQ发送消息
        long t = new Date().getTime();
//        amqpTemplate.convertAndSend("queueTestKey", t + "");
        Map<String, Object> result = new HashMap<String, Object>();
        try
        {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated())
            {
                //使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
            }
        } catch (Exception ex)
        {
            throw new Exception("验证失败");
        }
        result.put("success", true);
        return JSONUtils.toJSONString(result);
    }

    /**
     * redis的测试方法
     */
    private void testJedis()
    {
        redisTemplate.opsForValue().set("123", "12345");
        String result = redisTemplate.opsForValue().get("123");
        redisTemplate.delete("123");
        System.out.println("=====Redis缓存测试======：" + result);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout.json", method = RequestMethod.POST)
    @ResponseBody
    public String logout()
    {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return JSONUtils.toJSONString(result);
    }
}
