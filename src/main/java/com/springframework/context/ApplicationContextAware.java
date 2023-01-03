package com.springframework.context;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.Aware;

// 实现此接口，就能感知到所属的ApplicationContext
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext)throws BeansException;
}
