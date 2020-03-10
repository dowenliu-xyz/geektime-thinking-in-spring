package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 * <p>create at 2020/3/10</p>
 *
 * @author liufl
 * @since 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
