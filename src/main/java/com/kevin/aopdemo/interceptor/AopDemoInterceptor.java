package com.kevin.aopdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.kevin.aopdemo.anotation.LogRecord;
import com.kevin.aopdemo.enumeration.OperateTypeEnum;
import com.kevin.aopdemo.operate.OperatorInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 * @version： AopDemoInterceptor.java v 1.0, 2019年11月17日 17:54
 * @Description
 **/
public class AopDemoInterceptor implements HandlerInterceptor {

    private List<JSONObject> list = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取登录信息
        OperatorInfo userInfo = getUserInfo(request);
        if (userInfo != null) {
            System.out.println("获取到了用户信息");
        }

        // 方法拦截器
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            // 获取注解
            LogRecord record = h.getMethod().getDeclaringClass().getAnnotation(LogRecord.class);
            // 操作类型
            String operateTypeName;
            if (record.operateType() != 0) {
                operateTypeName = OperateTypeEnum.getNameByType(record.operateType());
            }

            // 需要记录的参数
            String[] content = record.recordContent();
            int length = content.length;
            if (content != null && length > 0) {
                for (int i = 0; i < length; i++) {
                    String[] strings = request.getParameterMap().get(content[i]);
                }

            }
            StringBuilder sb = new StringBuilder(1000);


        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 获取用户信息
     */
    private OperatorInfo getUserInfo(HttpServletRequest request) {
        OperatorInfo info = new OperatorInfo();
        info.setUserId(1L);
        info.setUserName("kevin");
        return info;
    }
}
