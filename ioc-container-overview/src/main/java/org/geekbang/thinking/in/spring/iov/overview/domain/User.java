package org.geekbang.thinking.in.spring.iov.overview.domain;

import org.geekbang.thinking.in.spring.iov.overview.enums.City;
import org.springframework.core.io.Resource;

/**
 * 用户类
 * <p>create at 2020/3/7</p>
 *
 * @author liufl
 * @since 1.0
 */
public class User {
    private Long id;
    private String name;
    private City city;
    private Resource configLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configLocation=" + configLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }
}
