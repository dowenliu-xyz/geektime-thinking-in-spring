package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 注解 BeanDefinition 示例
 * <p>create at 2020/3/7</p>
 *
 * @author liufl
 * @since 1.0
 */
// 3. 通过 @Import 来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 按照类型依赖查找
        System.out.println("Config 的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 的所有 Beans" + applicationContext.getBeansOfType(User.class));
        // 显示地关闭Spring应用上下文
        applicationContext.close();
    }

    // 2. 通过 @Component 方式
    @Component // 定义当前类作为 Spring Bean (组件）
    public static class Config {
        // 1. 通过 @Bean 方式定义
        /**
         * 通过 Java 注解的方式，定义了一个 Bean
         */
        @Bean(name = {"user", "xiaomage-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小马哥");
            return user;
        }
    }
}
