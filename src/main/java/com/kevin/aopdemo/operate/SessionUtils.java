package com.kevin.aopdemo.operate;

import com.kevin.aopdemo.dto.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author dell
 * @version： SessionUtils.java v 1.0, 2019年11月17日 18:54
 * @Description
 **/
public class SessionUtils {

    /**
     * 保存用户信息到 session
     */
    public static void saveUserInfo(UserModel model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 清除session
        session.removeAttribute(SessionConstants.SESSION_KEY);
        // 设置过期时间
        session.setAttribute(SessionConstants.SESSION_TIME_OUT, System.currentTimeMillis());
        // 放入用户信息
        OperatorInfo info = new OperatorInfo();
        BeanUtils.copyProperties(model, info);

        // 设置 session 信息
        session.setAttribute(SessionConstants.SESSION_KEY, info);
    }

    /**
     * 从session 从获取用户信息
     */
    public static OperatorInfo getOperator() {
        OperatorInfo operatorInfo = (OperatorInfo) ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession().getAttribute(SessionConstants.SESSION_KEY);
        if (null == operatorInfo) {
            return null;
        }
        return operatorInfo;
    }

    /**
     * 清除 session 中所有信息
     */
    public static void cleanUserInfo(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
