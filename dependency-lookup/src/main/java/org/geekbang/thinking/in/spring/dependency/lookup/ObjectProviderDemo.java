package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 * <p>create at 2020/3/8</p>
 *
 * @author liufl
 * @since 1.0
 */
public class ObjectProviderDemo { // @Configuration 是非必须注解。 PS.传给 register 方法时
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启用 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找
        lookupByObjectProvider(applicationContext);
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld() { // 方法名就是 Bean 名称 = "helloWorld"
        return "Hello, World";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
