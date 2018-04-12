package interceptor;

import org.apache.commons.lang3.StringUtils;
import util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
    实现了Filter接口，在servlet包里
    Filter也称之为过滤器，它是Servlet技术中最实用的技术，Web开发人员通过Filter技术，对web服务器管理的所有web资源：
    例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，
    从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。

    servlet filter和spring mvc Interceptor区别：
    1.拦截器是基于java的反射机制的，而过滤器是基于函数回调。
    2.拦截器不依赖与servlet容器，过滤器依赖与servlet容器。
    3.拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。
    4.拦截器可以访问action上下文、值栈里的对象，而过滤器不能访问。
    5.在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次。
    6.拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。
    servlet filter和spring mvc Interceptor执行顺序：

    ===========before doFilter1
    ===========before doFilter2
    ===========HandlerInterceptorAll preHandle
    ===========HandlerInterceptor1 preHandle
    ===========HandlerInterceptor2 preHandle
    执行Controller
    Controller return前
    ===========HandlerInterceptor2 postHandle
    ===========HandlerInterceptor1 postHandle
    ===========HandlerInterceptorAll preHandle
    Controller return后，Jsp加载完成
    ===========HandlerInterceptor2 afterCompletion
    ===========HandlerInterceptor1 afterCompletion
    ===========HandlerInterceptorAll preHandle
    ===========before doFilter2
    ===========before doFilter1
 */
public class SessionExpireFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //大公项目中用到的
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//        Json json = new Json();
//        json.setSuccess(true);
//        HttpServletRequest request = (HttpServletRequest)req;
//        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) res);
//        String uri = request.getRequestURI();
//        Cookie[] cookies = request.getCookies();
//        String sessinId = "";
//        //如果第一次使用，无cookie则放过请求
//        if(cookies == null || cookies.length == 0) {
//            chain.doFilter(req, res);
//            return;
//        }
//        for(Cookie cookie : cookies) {
//            if(cookie.getName().equals("sid")) {
//                sessinId = cookie.getValue();
//                break;
//            }
//        }
//        SessionInfo sessionInfo = SessionUtil.getSessionInfo(sessinId);
//        if((uri.contains("client_") && !uri.contains("client_login") && !uri.contains("client_sendMsg") && !uri.contains("client_regist")
//                && !uri.contains("client_checkPhoneOnly") ) || (uri.contains("service_") && !uri.contains("service_login")) ) {
//            if(sessionInfo == null || (sessionInfo.getLoginName() == null || "".equals(sessionInfo.getLoginName()))) {
//                json.setSuccess(false);
//                json.setMsg("login");
//                res.setContentType("text/html;charset=utf-8");
//                res.getWriter().write(JSON.toJSONString(json));
//                res.getWriter().flush();
//                res.getWriter().close();
//                return;
//            }
//        }
//        //切换系统，删除session
//        if(sessionInfo != null) {
//            if(sessionInfo.getSys().equals("manage")) {
//                if(uri.contains("client_") || uri.contains("service_")) {
//                    SessionUtil.deleSession(sessinId);
//                }
//            }else if(!uri.contains(sessionInfo.getSys())) {
//                SessionUtil.deleSession(sessinId);
//            }
//        }
//        chain.doFilter(req, res);
//        return;
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);

        if (StringUtils.isNotEmpty(loginToken)) {
            //判断logintoken是否为空或者""；
            //如果不为空的话，符合条件，继续拿user信息

//            String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//            User user = JsonUtil.string2Obj(userJsonStr, User.class);
//            if (user != null) {
//                //如果user不为空，则重置session的时间，即调用expire命令
//                RedisShardedPoolUtil.expire(loginToken, Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
//            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
