package com.springframework.context;

import java.util.EventObject;

/**
 * 抽象应用事件类
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
