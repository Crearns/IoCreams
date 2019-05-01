package com.cms.ioCreams.core.io;

public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        return new FileSystemResource(location);
    }

}
