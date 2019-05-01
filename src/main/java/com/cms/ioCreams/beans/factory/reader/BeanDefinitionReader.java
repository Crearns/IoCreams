package com.cms.ioCreams.beans.factory.reader;

import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(Resource resource) throws Exception;

    int loadBeanDefinitions(Resource... resources);

    int loadBeanDefinitions(String location);

    int loadBeanDefinitions(String... locations);

}
