package org.geekbang.thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Bean 垃圾回收（GC）示例
 * <p>create at 2020/3/8</p>
 *
 * @author liufl
 * @since 1.0
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class （配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
        TimeUnit.SECONDS.sleep(5);
        // 提醒触发 GC
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }
}
