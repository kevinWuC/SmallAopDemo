package com.kevin.aopdemo.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kevin.aopdemo.anotation.LogRecord;
import com.kevin.aopdemo.dto.LogRecordDTO;
import com.kevin.aopdemo.enumeration.OperateTypeEnum;
import com.kevin.aopdemo.operate.OperatorInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @author dell
 * @version： LogRecordAspect.java v 1.0, 2019年11月17日 17:18
 * @Description
 **/
@Aspect
@Component
@Slf4j
public class LogRecordAspect {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.kevin.aopdemo.anotation.LogRecord)")
    public void pointcut() {
    }


    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LogRecordDTO recordDTO = new LogRecordDTO();
        // 执行j结果
        Object result = joinPoint.proceed();
        // 获取注解
        LogRecord record = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LogRecord.class);
        // 操作类型
        if (record.operateType() != 0) {
            recordDTO.setOperateTypeName(OperateTypeEnum.getNameByType(record.operateType()));
        }

        // 需要记录的参数
        String paramsNeeded = getParamsNeeded(record, joinPoint);
        if (StringUtils.isNotBlank(paramsNeeded)) {
            paramsNeeded = paramsNeeded.substring(0, paramsNeeded.lastIndexOf(","));
            recordDTO.setRecordContent(paramsNeeded);
        }

        // 操作人、时间
        saveOperatorInfo(recordDTO);

        // 本次请求的结果
        if (result == null) {
            return "";
        }

        // TODO 如果是导出

        String resultStr = JSON.toJSONString(result);
        System.out.println("————————————————————————");
        System.out.println("s:" + resultStr);
        System.out.println("recordDTO:" + recordDTO);
        System.out.println("————————————————————————");
        return result;
    }

    /**
     * 获取需要记录的参数
     *
     * @param record
     * @param joinPoint
     * @return
     */
    private String getParamsNeeded(LogRecord record, ProceedingJoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        String[] content = record.recordContent();
        int length = content.length;
        if (content != null || length > 0) {
            // 处理请求参数
            Object[] args = joinPoint.getArgs();
            int len = args.length;
            if (args != null && len > 0) {
                JSONObject object;
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < content.length; j++) {
                        // 过滤出需要记录的参数、值
                        Object paramsValue = new BeanMap(args[i]).get(content[j]);
                        if (paramsValue == null) {
                            continue;
                        }
                        object = new JSONObject();
                        object.put(content[i], paramsValue);
                        builder.append(object).append(",");
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    private OperatorInfo getOperatorInfo() {
        OperatorInfo info = new OperatorInfo();
        info.setUserId(1L);
        info.setUserName("Hello AOP");
        return info;

    }

    /**
     * 记录操作人、操作时间
     *
     * @param recordDTO
     */
    private void saveOperatorInfo(LogRecordDTO recordDTO) {
        if (getOperatorInfo() != null) {
            if (getOperatorInfo().getUserId() != null) {
                recordDTO.setOperatorId(getOperatorInfo().getUserId());
            }
            if (StringUtils.isNotBlank(getOperatorInfo().getUserName())) {
                recordDTO.setOperatorName(getOperatorInfo().getUserName());
            }
        }
        recordDTO.setOperatorTime(new Date());
    }

}
