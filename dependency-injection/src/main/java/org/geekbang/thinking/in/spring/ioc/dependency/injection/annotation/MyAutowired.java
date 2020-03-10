package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注入注解（元标注 @Autowired）
 * <p>create at 2020/3/10</p>
 *
 * @author liufl
 * @since 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
