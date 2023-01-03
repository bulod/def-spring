package com.springframework.beans.factory.config;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    /*
     执行BeanPostProcessor接口实现类的postProcessBeforeInitial方法
     * */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /*
     执行BeanPostProcessor接口实现类的postProcessAfterInitial方法
     * */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
