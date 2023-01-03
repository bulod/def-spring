package com.springframework.context.event;

import com.springframework.context.ApplicationEvent;
import com.springframework.context.ApplicationListener;


// 事件广播器
public interface ApplicationEventMulticaster {
    // 添加要通知所有事件的监听器
    void addApplicationListener(ApplicationListener<?> listener);
    // 从通知列表中删除监听器
    void removeApplicationListener(ApplicationListener<?> listener);
    // 将给定的应用程序事件多播到适当的监听器
    void multicastEvent(ApplicationEvent event);
}
