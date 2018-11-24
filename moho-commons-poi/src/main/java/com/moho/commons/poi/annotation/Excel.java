package com.moho.commons.poi.annotation;

import java.lang.annotation.*;

/**
 * 生成Excel标题栏注解
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 * @author chasdream
 * @date 2018-11-24
 * @version 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Excel {

    /**
     * 生成标题栏
     *
     * @return
     */
    String title() default "";

    /**
     * 枚举转换, 格式: {'key1':'val1', 'key2':'val2'}
     * @return
     */
    String convert() default "";
}
