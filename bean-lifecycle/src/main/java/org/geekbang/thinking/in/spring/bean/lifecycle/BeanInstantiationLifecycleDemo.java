package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期示例
 * <p>create at 2020/3/13</p>
 *
 * @author liufl
 * @since 1.0
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();

        System.out.println("--------------------------------");

        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location, "META-INF/bean-constructor-dependency-injection.xml");
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations("META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml");

        // 启动应用上下文
        applicationContext.refresh();

        // 通过 Bean Id 和类型进行依赖查找
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User superUser = applicationContext.getBean("superUser", User.class);
        System.out.println(superUser);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        // 关闭应用上下文
        applicationContext.close();
    }

}
