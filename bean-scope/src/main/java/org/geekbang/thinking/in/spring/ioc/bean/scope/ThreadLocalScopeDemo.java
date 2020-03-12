package org.geekbang.thinking.in.spring.ioc.bean.scope;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 * <p>create at 2020/3/12</p>
 *
 * @author liufl
 * @since 1.0
 */
public class ThreadLocalScopeDemo {
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return createUser();
    }

    public static User createUser() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 Scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        // 启动应用上下文
        applicationContext.refresh();

        scopeBeansByLookup(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                // user 是共享 Bean 对象
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id: %d]user = %s%n", Thread.currentThread().getId(), user);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
