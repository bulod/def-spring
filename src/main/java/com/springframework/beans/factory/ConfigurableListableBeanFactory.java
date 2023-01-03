package com.springframework.beans.factory;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.config.BeanPostProcessor;
import com.springframework.beans.factory.config.ConfigurableBeanFactory;


/*
 ConfigurableListableBeanFactory定义了：
 1.根据bean名称获取BeanDefinition数据方法
 2.preInstantiateSingletons()实现创建bean并存储单例bean方法
 3.添加BeanPostProcessor到集合中的方法
**/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    // 此方法是自定义实例化完，需要将某些对象更改的对象进行实例化
    void preInstantiateSingletons() throws BeansException;
    // 此方法是在自定义类都实例化完毕，开始存储BeanPostProcessor到集合中
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
