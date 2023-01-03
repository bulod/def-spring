package com.springframework.beans.factory;

// 定义标记接口,实现该接口可以被spring感知
// 就像是一种标签一样，可以统一方便摘取出属于此类接口的实现类，通常会有instanceof一起判断使用
public interface Aware {
}
