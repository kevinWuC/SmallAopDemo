package com.kevin.aopdemo.anotation;

import java.lang.annotation.*;

/**
 * @author dell
 * @version： LogRecord.java v 1.0, 2019年11月17日 17:17
 * @Description
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogRecord {

    /**
     * 操作类型
     */
    int operateType() default 0;

    /**
     * 记录内容
     */
    String[] recordContent() default {};
}
