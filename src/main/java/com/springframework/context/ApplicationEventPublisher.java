package com.springframework.context;
// 事件发布者定义
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
