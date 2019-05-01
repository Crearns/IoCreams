package com.cms.ioCreams.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface Resource {

    /**
     * 资源是否存在
     */
    boolean exists();

    /**
     * 资源是否可读
     */
    default boolean isReadable() {
        return true;
    }

    /**
     * 资源所代表的句柄是否被一个 stream 打开了
     */
    default boolean isOpen() {
        return false;
    }

    /**
     * 是否为 File
     */
    default boolean isFile() {
        return false;
    }

    /**
     * 返回资源的 File 的句柄
     */
    File getFile() throws IOException;

    /**
     * 资源内容的长度
     */
    long contentLength() throws IOException;

    /**
     * 资源最后的修改时间
     */
    long lastModified() throws IOException;


    /**
     * 获取文件字节流
     */
    default InputStream getInputStream() throws IOException {
        try {
            return new FileInputStream(getFile());
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 资源的文件名
     */
    String getFilename();

    /**
     * 资源的描述
     */
    String getDescription();

}
