package authority;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/*
权限的实现思路：
开始我采用的是建用户表,角色表,权限表，
之后在拦截器中对每一个请求进行拦截，再到数据库中进行查询看当前用户是否有该权限，
这样的设计能满足大多数中小型系统的需求。不过这篇所介绍的Shiro能满足之前的所有需求，
并且使用简单，安全性高，而且现在越来越的多企业都在使用Shiro，这应该是一个收入的你的技能库。
 */

/*
Apache Shiro是apache的一个强大而灵活的开源安全框架,它干净利落的处理身份认证、授权、企业会话管理和加密.
Shiro首要的目标是易于使用和理解,提供一个干净而直观的API,来简化开发人员在使用它们的应用程序安全上的努力.
这也是Shiro可能并没有Spring Security使用的人却越来越多的因素之一吧.
Shiro不强制依赖其他第三方框架、容器、或者应用服务器,不仅可以应用在JavaSE环境,也可以用在JavaEE环境.
 */

/*
使用场景：
登录的验证
对指定角色的验证
对URL的验证
 */

public class MyShiroRealm extends AuthorizingRealm {

    /*
    Shiro自定义域，方法可以自己实现
     */

    //这里因为没有调用后台，直接默认只有一个用户("gwb"，"123456")
    private static final String USER_NAME = "gwb";
    private static final String PASSWORD = "123456";

    /*
     * 授权 用于的权限的认证。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        roleNames.add("administrator");//添加角色
        permissions.add("newPage.do");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    /*
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if (token.getUsername().equals(USER_NAME)) {
            return new SimpleAuthenticationInfo(USER_NAME, PASSWORD, getName());
        } else {
            throw new AuthenticationException();
        }
    }

}