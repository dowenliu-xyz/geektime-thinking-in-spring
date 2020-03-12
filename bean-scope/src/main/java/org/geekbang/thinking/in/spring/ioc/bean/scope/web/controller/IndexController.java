package org.geekbang.thinking.in.spring.ioc.bean.scope.web.controller;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring Web MVC Controller
 * <p>create at 2020/3/12</p>
 *
 * @author liufl
 * @since 1.0
 */
@Controller
public class IndexController {
    @Autowired
    private User user; // CGLIB 代理后对象，不变

    @GetMapping("index.html")
    public String index(Model model) {
        // JSP EL 变量搜索路径 page -> request -> session -> application(ServletContext)
        // userObject -> page Context
        // user 对象在 ServletContext 上下文， 名称 user
        model.addAttribute("userObject", user);
        return "index";
    }
}
