package com.springframework.beans.factory.config;

public interface SingletonBeanRegistry {
/*此注册接口为什么只提供了getSingleton(),而没有提供addSingleton()，所有实现此接口的都具有了获取单例对象的权利，
但是没有保存单例对象的权利也就可以保证安全，而不是任意的子实现类都可以去保存添加单例对象，这样可以指定想要的实现类自己去添加保存实例，
如：DefaultSingletonBeanRegistry实现类
* */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
