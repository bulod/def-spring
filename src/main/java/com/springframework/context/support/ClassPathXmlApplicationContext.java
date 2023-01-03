package com.springframework.context.support;

import com.springframework.beans.BeansException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    // 获取配置文件的路径集合
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从xml中加载BeanDefinition并刷新上下文。
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从xml中加载BeanDefinition并刷新上下文。
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations)throws BeansException{
        this.configLocations=configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
