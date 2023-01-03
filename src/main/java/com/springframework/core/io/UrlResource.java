package com.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

// 通过http方式读取文件，我们可以把文件放入不同的服务器或者云上
public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try{
            return con.getInputStream();
        }catch (IOException ex){
            if(con instanceof HttpURLConnection){
                ((HttpURLConnection)con).disconnect();
            }
            throw ex;
        }
    }
}
