package org.geekbang.thinking.in.spring.iov.overview.dependency.injection;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;
import org.geekbang.thinking.in.spring.iov.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入示例
 * <p>create at 2020/3/7</p>
 *
 * @author liufl
 * @since 1.0
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory()); // 打印依赖注入的BeanFactory
        System.out.println(userRepository.getBeanFactory() == beanFactory); // false 表明注入的BeanFactory并不是当前BeanFactory

        try {
            System.out.println(beanFactory.getBean(BeanFactory.class)); // 抛出异常。BeanFactory中并没有BeanFactory实例Bean
        } catch (Throwable thr) {
            thr.printStackTrace();
        }

        ObjectFactory<User> userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject());

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == beanFactory); // true 使用延时加载注入的ApplicationContext是当前BeanFactory
    }
}
