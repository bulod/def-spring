package com.springframework.beans.factory;

// 实现此接口，既能感知到所属的ClassLoader
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
