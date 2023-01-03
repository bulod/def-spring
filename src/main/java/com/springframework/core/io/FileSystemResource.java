package com.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// 本地文件流处理
public class FileSystemResource implements Resource{
    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        // 有文件就能获取路径
        this.path=file.getPath();
    }

    public FileSystemResource(String path) {
        // 有路径就能创建文件
        this.file=new File(path);
        this.path = path;
    }

    // 通过指定文件路径的方式读取文件信息
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public final String getPath(){
        return this.path;
    }
}
