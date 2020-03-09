package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.iov.overview.domain.User;

/**
 * {@link User} 的 Holder 类
 * <p>create at 2020/3/9</p>
 *
 * @author liufl
 * @since 1.0
 */
public class UserHolder {
    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        System.out.println("UserHolder: constructor");
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println("UserHolder: setter");
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
