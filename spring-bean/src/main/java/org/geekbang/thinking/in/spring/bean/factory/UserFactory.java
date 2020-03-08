package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;

/**
 * {@link User} 工厂
 * <p>create at 2020/3/7</p>
 *
 * @author liufl
 * @since 1.0
 */
public interface UserFactory {
    default User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }
}
