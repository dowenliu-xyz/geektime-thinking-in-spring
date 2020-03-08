package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 * <p>create at 2020/3/8</p>
 *
 * @author liufl
 * @since 1.0
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
