package org.geekbang.thinking.in.spring.ioc.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例
 * <p>create at 2020/3/9</p>
 *
 * @author liufl
 * @since 1.0
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 创建 Spring 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 BeanDefinition Bean
        // Class 是一个 POJO 普通类，不过初始化方法回调时抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());
        // 启动应用上下文
        applicationContext.refresh();
        // 关闭应用上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        @PostConstruct // CommonAnnotationBeanPostProcessor
        public void init() throws Exception {
            throw new Exception("init() : For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("For purpose...");
        }
    }
}
