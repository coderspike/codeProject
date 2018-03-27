package base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller层的公共父类，将数据转为json格式并传递至前台，可以获取request、response、session、
 * servletContext。
 * 抽象封装，供controller继承
 */
public class BaseController {

    /**
     * HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，
     * HTTP请求头中的所有信息都封装在这个对象中，通过这个对象提供的方法，可以获得客户端请求的所有信息。
     */

    private static final String JSON_ATTRIBUTE_NAME = "json";
    protected ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
    protected ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

    //ThreadLocal:使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改。

    /**
     * 在每个请求前先执行此方法
     *
     * @param req
     * @param res
     * @ModelAttribute 在每个请求前先执行此方法
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest req, HttpServletResponse res) {
        request.set(req);
        response.set(res);
    }

    /**
     * 将对象转换成JSON字符串，并响应回前台
     *
     * @param object
     * @throws IOException
     */
    public void writeJson(Object object) {
        try {
            String json = JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
            HttpServletResponse res = this.response.get();
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().write(json);
            res.getWriter().flush();
            res.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Json的状态信息
     *
     * @return
     */
    public Json getJson() {
        HttpServletRequest req = this.request.get();
        Json json = (Json) req.getAttribute(JSON_ATTRIBUTE_NAME);
        if (json == null) {
            json = new Json(true, "操作成功！");
            req.setAttribute(JSON_ATTRIBUTE_NAME, json);
        }
        return json;
    }

    /**
     * 获取ModelAndView
     *
     * @param attributeName  参数名，在前台可以用EL表达式直接获取
     * @param attributeValue 参数值，可以为对象、集合、基本数值类型
     * @param viewName       视图名，指定返回的页面路径
     * @return
     */
    public ModelAndView getModelAndView(String attributeName, Object attributeValue, String viewName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(attributeName, attributeValue);
        mv.setViewName(viewName);
        return mv;
    }

    /**
     * 基于@ExceptionHandler注解声明处理异常
     * 各个Controller需要对异常处理的，可继承该类处置
     */
    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception exc) {
        //通过具体产生异常的coontroller实例化logger对象，记录详细的堆栈信息
        Class claz = this.getClass();
        Logger logger = LoggerFactory.getLogger(claz);
        logger.error(" 访问：：：" + request.getRequestURI() + "：：：：出现异常：：：：" + exc);
        for (StackTraceElement stack : exc.getStackTrace()) {
            logger.error(stack.toString());
        }
        Json j = new Json();
        String resultPath = null;
        //根据不同错误转向不同页面，这里可跳转页面，也可封装信息；
        String exceptionMsg = getExceptionMsg(exc);//获得异常信息
        j.setMsg("操作无效！" + exceptionMsg);
        j.setSuccess(false);
        //把自定义错误信息 ，发送错误消息到页面  或通过writeJson写入封装Json对象
        request.setAttribute("exc", exc);
        request.setAttribute("msg", exceptionMsg);
        writeJson(j);
        resultPath = "system/exception/exceptionJsp/error-business";
        return resultPath;
    }

    /**
     * 根据异常对象获得异常信息
     *
     * @param e 系统封装未捕获异常
     * @return 异常错误信息
     * @author LGF
     */
    private String getExceptionMsg(Exception e) {
        String exceptionMsg = "请联系管理员。";
        if (e instanceof IOException) {
            exceptionMsg = "网络IO异常！";
        }
        return exceptionMsg;
    }

    public ThreadLocal<HttpServletRequest> getRequest() {
        return request;
    }

    public void setRequest(ThreadLocal<HttpServletRequest> request) {
        this.request = request;
    }

    public ThreadLocal<HttpServletResponse> getResponse() {
        return response;
    }

    public void setResponse(ThreadLocal<HttpServletResponse> response) {
        this.response = response;
    }
}
