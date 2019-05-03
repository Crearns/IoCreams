package com.cms.ioCreams.beans.factory.xml;

import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;

import java.util.Map;

public interface BeanDefinitionReader {
    ResourceLoader getResourceLoader();

    Map<String, BeanDefintion> loadBeanDefinitions(Resource resource) throws Exception;

    int loadBeanDefinitions(Resource... resources);

    int loadBeanDefinitions(String location);

    int loadBeanDefinitions(String... locations);

}
