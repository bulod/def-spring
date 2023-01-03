package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.DisposableBean;
import com.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    //单例池
    private Map<String,Object> singletonObjects=new HashMap<String, Object>();
    // 要吊销的bean容器
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
    // 注册销毁容器
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            // 将容器中销毁的对象remove
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                // 调用用户自定义实现的销毁接口
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
