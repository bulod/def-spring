package com.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

//资源加载的接口
public interface Resource {
    InputStream getInputStream() throws IOException;
}
