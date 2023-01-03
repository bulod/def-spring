package com.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path,(ClassLoader)null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        //spring的断言，如果为空就报错不能继续往下走，必须非空
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = (classLoader !=null?classLoader: ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 需要动态获取某个位置的文件可以获取文件的资源，可以采用classLoader.getResourceAsStream
        InputStream is = classLoader.getResourceAsStream(path);
        if(is==null){
            throw new FileNotFoundException(this.path +"cannot be opened because it does not exist");
        }
        return is;
    }
}
