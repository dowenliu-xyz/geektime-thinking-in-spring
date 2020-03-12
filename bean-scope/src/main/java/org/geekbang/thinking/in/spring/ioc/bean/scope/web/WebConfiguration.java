package org.geekbang.thinking.in.spring.ioc.bean.scope.web;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 * <p>create at 2020/3/12</p>
 *
 * @author liufl
 * @since 1.0
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {
    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }
}
