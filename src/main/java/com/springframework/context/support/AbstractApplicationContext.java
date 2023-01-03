package com.springframework.context.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.springframework.beans.factory.config.BeanPostProcessor;
import com.springframework.context.ApplicationEvent;
import com.springframework.context.ApplicationListener;
import com.springframework.context.ConfigurableApplicationContext;
import com.springframework.context.event.ApplicationEventMulticaster;
import com.springframework.context.event.ContextClosedEvent;
import com.springframework.context.event.ContextRefreshedEvent;
import com.springframework.context.event.SimpleApplicationEventMulticaster;
import com.springframework.core.io.DefaultResourceLoader;
import com.springframework.core.io.Resource;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 1.创建beanFactory
        // 将创建完的beanFactory和配置文件路径传输到资源处理器里
        // 从资源处理器获取输入流，根据流解析bean以及属性数据并注册到bean容器里
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.  在bean实例化之前，执行BeanFactoryPostProcessor(Invoke factory processors registered as beans in the context)
        // 4.1 根据BeanFactoryPostProcessor来判断是否是自定义实现子类，是就创建bean，并注册到单例类（创建自定义类Bean(MyBeanFactoryPostProcessor))
        // 4.2 根据得到的BeanFactoryPostProcessor接口来调用postProcessBeanFactory方法(此时beanDefinition里的属性已经修改)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5.  BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        // 5.1 通过getBeansOfType()找到与BeanPostProcessor相关的类，
        // 5.2 找到以后调用将当前的BeanPostProcessor放入集合里（做标识-后续其他bean实例创建可进行后置处理更改对象bean）
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        // 8.提前实例化单例bean对象
        //   通过beanDefinitionMap里的实例对象将后续bean实例进行实例化，并处理后置修改的对象
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    // 在bean实例化之前，执行BeanFactoryPostProcessor，自定义实现进行bean的修改
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 获取和BeanFactoryPostProcessor类有关系的的类-这里主要是获取BeanFactoryPostProcessor的子实现接口
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            // 调用用户自定义实现的子接口
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 根据BeanPostProcessor获取其子实现接口
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            // 将beanPostProcessor添加到集合里
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }
    // 将配置文件的关于监听的配置进行注册存储容器里
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    // 在程序正常退出或jvm进行关闭时调用传输进来的线程对象方法，此次调用本类里close()方法
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
