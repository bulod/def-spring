package com.springframework.core.io;


import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;
// 根据不同的方式创建不同的资源
public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location must not be null");
        // 检测字符串是否以指定的前缀开始
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            try {
                URL url=new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException e){
                return new FileSystemResource(location);
            }
        }
    }
}
