package com.springframework.beans.factory.config;

import com.springframework.beans.PropertyValue;
import com.springframework.beans.PropertyValues;


//定义bean的实例化信息
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    //在xml注册Bean定义时，通过scope字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        // 此处加入new PropertyValues()目的是在Bean对象没有属性时后续获取会报空错，在此处理
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues !=null?propertyValues:new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getSCOPE_SINGLETON() {
        return SCOPE_SINGLETON;
    }

    public String getSCOPE_PROTOTYPE() {
        return SCOPE_PROTOTYPE;
    }

    public String getScope() {
        return scope;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setSCOPE_SINGLETON(String SCOPE_SINGLETON) {
        this.SCOPE_SINGLETON = SCOPE_SINGLETON;
    }

    public void setSCOPE_PROTOTYPE(String SCOPE_PROTOTYPE) {
        this.SCOPE_PROTOTYPE = SCOPE_PROTOTYPE;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }
}
