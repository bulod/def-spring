package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;


public interface InstantiationStrategy {
    // 添加必要的入参信息,还有Constructor：必要的类信息，目的是为了拿到符合入参信息相对应的构造函数
    // args是具体的入参信息，最终实例化时候会用到
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args)throws BeansException;

}
