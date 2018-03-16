package util;

import authority.SessionInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * SessionUtil 针对shiro的
 */
public class SessionUtil {
    /**
     * 获取当前session的sessionInfo
     *
     * @return
     */
    public static SessionInfo getSessionInfo() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
        return sessionInfo;
    }

    /**
     * 获取当前sessionId
     *
     * @return
     */
    public static String getSessionInfoId() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (String) session.getId();
    }

    /**
     * 根据sessionID获取session
     *
     * @param sessionId
     * @return
     */
    public static SessionInfo getSessionInfo(String sessionId) {
        Subject currentUser = new Subject.Builder().sessionId(sessionId).buildSubject();
        Session session = currentUser.getSession();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
        return sessionInfo;
    }

    /**
     * 删除session
     *
     * @param sessionId
     * @return
     */
    public static boolean deleSession(String sessionId) {
        Subject currentUser = new Subject.Builder().sessionId(sessionId).buildSubject();
        Session session = currentUser.getSession();
        session.setTimeout(0);
        return true;
    }

}
