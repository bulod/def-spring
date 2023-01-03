package com.springframework.beans.factory;

import com.springframework.beans.BeansException;

// 特点：此接口类继承Aware接口类
// 实现此接口，就能感知到所属的beanFactory
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
