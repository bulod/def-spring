package com.springframework.beans.factory;

// 实现此接口，就能感知到所属的beanName
public interface BeanNameAware extends Aware{
    void setBeanName(String name);
}
