package com.tangjianbo.reflection;

import com.tangjianbo.reflection.controller.UserController;
import com.tangjianbo.reflection.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 18482
 */
public class TestReflect {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserController userController = new UserController();
        //拿到当前对象所属的类
        Class<? extends UserController> clazz = userController.getClass();
        UserService userService = new UserService();
        System.out.println(userService instanceof  UserService);
        System.out.println(userService);
        //由于属性是私有的，所以这里需要通过公有方法才可以设置值
        Field field = clazz.getDeclaredField("userService");
        String name = field.getName();
        //获取类中的方法名 setUserService()
        String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        System.out.println(methodName);

        Method method = clazz.getMethod(methodName, UserService.class);
        //反射方法增强 设置userService 属性
        method.invoke(userController, userService);

        System.out.println(userController.getUserService());


    }

}
