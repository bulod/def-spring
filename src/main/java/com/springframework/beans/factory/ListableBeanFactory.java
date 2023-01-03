package com.springframework.beans.factory;

import com.springframework.beans.BeansException;

import java.util.Map;

// ListableBeanFactory定义了
// 1.根据指定类获取其有关系的类，2.获取所有BeanDefinition容器里的bean名称
public interface ListableBeanFactory extends BeanFactory{
    // 此方法主要是为了区分哪些是自定义类，这样后续就可将自定义类方法调用即可达到想要的目的
    // 从左往右，第一个T标识<T>是泛型
    //         第二个T是返回Map<String,T>
    //         第三个T是参数控制为泛型
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    // 返回注册表中所有的bean名称
    String[] getBeanDefinitionNames();
}
