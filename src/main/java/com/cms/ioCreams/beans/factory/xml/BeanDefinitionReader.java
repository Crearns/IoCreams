package com.cms.ioCreams.beans.factory.xml;

import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;
import com.cms.ioCreams.core.io.support.EncodedResource;

import java.util.Map;

public interface BeanDefinitionReader {
    ResourceLoader getResourceLoader();


    Map<String, BeanDefintion> loadBeanDefinitions(EncodedResource encodedResource) throws Exception;

}
