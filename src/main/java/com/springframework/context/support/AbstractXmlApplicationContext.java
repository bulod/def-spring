package com.springframework.context.support;

import com.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.springframework.beans.factory.xml.XmlBeanDefinitionReader;

// AbstractXmlApplicationContext抽象类主要是调用解析xml中的bean并加载到系统里，
// 继承AbstractRefreshableApplicationContext类用来创建beanFactory然后定义loadBeanDefinitions让当前子类实现
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(null!=configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}