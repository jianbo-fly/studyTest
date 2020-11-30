package com.tangjianbo.reflection.controller;

import com.tangjianbo.reflection.service.UserService;

public class UserController {
    /**
     * 使用反射的方式注入userService对象   详情请见test
     */

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
