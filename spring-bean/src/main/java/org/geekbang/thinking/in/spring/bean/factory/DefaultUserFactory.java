package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * 默认 {@link UserFactory} 实现
 * <p>create at 2020/3/7</p>
 *
 * @author liufl
 * @since 1.0
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {


    // 1. 基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory(): UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertisSet : UserFactory 初始化中... ");
    }
}
