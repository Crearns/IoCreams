package com.cms.ioCreams.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractResource implements Resource {

    @Override
    public boolean exists() {
        try {
            return getFile().exists();
        } catch (IOException e) {
            try {
                InputStream is = getInputStream();
                is.close();
                return true;
            } catch (IOException e1) {
                return false;
            }

        }
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
    }

    @Override
    public long contentLength() throws IOException {
        InputStream is = getInputStream();
        try {
            long size = 0;
            byte[] buf = new byte[255]; // 每次最多读取 255 字节
            int read;
            while ((read = is.read(buf)) != -1) {
                size += read;
            }
            return size;
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
            }
        }

    }

    @Override
    public long lastModified() throws IOException {
        long lastModified = getFileForLastModifiedCheck().lastModified();
        if (lastModified == 0L) {
            throw new FileNotFoundException(getDescription() +
                    " cannot be resolved in the file system for resolving its last-modified timestamp");
        }
        return lastModified;
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    /**
     * 返回资源的描述
     */
    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this ||
                (obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription())));
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }
}
