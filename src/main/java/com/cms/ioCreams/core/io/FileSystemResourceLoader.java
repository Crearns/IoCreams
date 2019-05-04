package com.cms.ioCreams.core.io;

public class FileSystemResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }
}
