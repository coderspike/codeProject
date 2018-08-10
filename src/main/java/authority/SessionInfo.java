package authority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SessionInfo implements Serializable {

    private String sys;// 访问系统 client, service,manage
    private String sessionId;   // 终端登录sessionId
    private String useId; // 用户Id,客户端使用
    private String userName;//用户名
    private List<String> methodAuthority; // 方法权限级别，get*,delete*,edit*,find*
    private String loginName; //登录名
    private String roleIds; //角色ids
    private String roleNames;//角色名称
    private String applyFlowId;//项目ID

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getMethodAuthority() {
        return methodAuthority;
    }

    public void setMethodAuthority(List<String> methodAuthority) {
        this.methodAuthority = methodAuthority;
    }

    public Date getNowTime() {
        return new Date();
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getApplyFlowId() {
        return applyFlowId;
    }

    public void setApplyFlowId(String applyFlowId) {
        this.applyFlowId = applyFlowId;
    }
}