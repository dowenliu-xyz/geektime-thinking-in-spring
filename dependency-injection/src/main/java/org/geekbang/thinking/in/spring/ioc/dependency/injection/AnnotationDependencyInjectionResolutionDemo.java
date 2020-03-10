package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 * <p>create at 2020/3/9</p>
 *
 * @author liufl
 * @since 1.0
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {
    @Inject
    private User injectedUser;

    // 依赖查找 + 延迟
    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private Optional<User> userOptional; // superUser

    // 集合类型的依赖注入
    @Autowired
    private Map<String, User> users; // user superUser

    // 依赖查找（处理）
    // DependencyDescriptor ->
    // 必须（required = true）
    // 实时注入 (eager = true)
    // 通过类型（User.class）
    // 字段名称（"user"）
    // 是否首要（primary = true）
    @Autowired
    private User user;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // 期待输出 superUser Bean
        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.injectedUser = " + demo.injectedUser);
        // 期待输出 user superUser
        System.out.println("demo.users = " + demo.users);
        // 期待输出 superUser Bean
        System.out.println("demo.userOptional = " + demo.userOptional);

        // 关闭应用上下文
        applicationContext.close();
    }
}
