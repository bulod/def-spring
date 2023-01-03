package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
                if (null != ctor) {
                    // 实例化有参构造函数,并入参
                    return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
                } else {
                    // 实例化无参构造函数
                    return clazz.getDeclaredConstructor().newInstance();
                }
        } catch (InstantiationException |IllegalAccessException |InvocationTargetException |NoSuchMethodException e){
                throw new BeansException("Failed to instantiate ["+clazz.getName()+"]",e);
        }
    }
}
