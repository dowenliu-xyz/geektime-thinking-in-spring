package org.geekbang.thinking.in.spring.iov.overview.dependency.injection;

import org.geekbang.thinking.in.spring.iov.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

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

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory()); // 打印依赖注入的BeanFactory
        System.out.println(userRepository.getBeanFactory() == beanFactory); // false 表明注入的BeanFactory并不是当前BeanFactory
        // false 原因：通过ClassPathXmlApplicationContext创建的是一个代理类，不是底层的BeanFactory。 参考 AbstractRefreshableApplicationContext#beanFactory

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory); // true 使用延时加载注入的ApplicationContext是当前BeanFactory

//        try {
//            System.out.println(beanFactory.getBean(BeanFactory.class)); // 抛出异常。BeanFactory中并没有BeanFactory实例Bean
//        } catch (Throwable thr) {
//            thr.printStackTrace();
//        }

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean：" + environment);
    }
}
