package interceptor;

import org.apache.commons.lang3.StringUtils;
import util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
