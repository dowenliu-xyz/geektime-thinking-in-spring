package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解的依赖 Field 方法注入示例
 * <p>create at 2020/3/9</p>
 *
 * @author liufl
 * @since 1.0
 */
public class AnnotationDependencyFieldInjectionDemo {
    @Autowired
    private
//    static  // @Autowired 会忽略静态字段
    UserHolder userHolder;

    // @Resource 不能注入静态字段，会抛出异常
    // @Resource 优先以 byName 方式注入，如果没有对应 Bean 会再以 byType 方式查找
//    @Resource
//    private static User staticUser;
    @Resource
    User user;
    @Resource
    User superUser;
    @Resource
    User unknownUser;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        // @Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);

        System.out.println(demo.user);
        System.out.println(demo.superUser);
        System.out.println(demo.user == demo.superUser);

        System.out.println(demo.unknownUser);
        System.out.println(demo.unknownUser == applicationContext.getBean(User.class));
        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        /*/
        return new UserHolder(user);
        /*/
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
        //*/
    }
}
