package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.core.io.Resource;
import com.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    //getRegistry()和getResourceLoader()都是用于提供给这三个方法的工具，加载和注册
    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
