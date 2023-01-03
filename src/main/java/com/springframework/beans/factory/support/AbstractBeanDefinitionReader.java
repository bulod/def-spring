package com.springframework.beans.factory.support;

import com.springframework.core.io.DefaultResourceLoader;
import com.springframework.core.io.ResourceLoader;

// bean定义读取的准备工作，定义成abstract就可以任意实现需要的类，不想实现的就可不实现
// 此类主要是获取BeanDefinitionRegistry和ResourceLoader
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry,ResourceLoader resourceLoader){
        this.registry=registry;
        this.resourceLoader=resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
