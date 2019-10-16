package com.lin.common.annotation;



import com.lin.enums.LogType;

import java.lang.annotation.*;

/**
 * 系统日志
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 日志描述
     *
     * @return
     */
    String description() default "";

    /**
     * 日志类型
     *
     * @return
     */
    LogType type() default LogType.OPERATION;

}
