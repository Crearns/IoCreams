package com.cms.ioCreams.beans.factory.config;

import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;
import com.cms.ioCreams.core.io.support.EncodedResource;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


public class DefaultBeanDefinitionReader implements BeanDefinitionReader {

    Logger logger = Logger.getLogger(DefaultBeanDefinitionReader.class);

    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded = new ThreadLocal<>();



    @Override
    public ResourceLoader getResourceLoader() {
        return null;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws Exception {
        return loadBeanDefinitions(new EncodedResource(resource));
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String location) {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String... locations) {
        return 0;
    }

    public int loadBeanDefinitions(EncodedResource encodedResource) throws Exception {
        logger.info("加载 BeanDefinition");
        Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();
        if (currentResources == null) {
            currentResources = new HashSet<>(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }

        if (!currentResources.add(encodedResource)) { // 将当前资源加入记录中。如果已存在，抛出异常
            throw new Exception("Detected cyclic loading of " + encodedResource + " - check your import definitions!");
        }

        try {
            InputStream inputStream = encodedResource.getResource().getInputStream();
            try {
                InputSource inputSource = new InputSource(inputStream);
                if (encodedResource.getEncoding() != null) { // 设置编码
                    inputSource.setEncoding(encodedResource.getEncoding());
                }
                // 执行加载 BeanDefinition
                return doLoadBeanDefinitions(inputSource, encodedResource.getResource());
            } finally {
                inputStream.close();
            }
        }finally {
            currentResources.remove(encodedResource);
            if (currentResources.isEmpty()) {
                this.resourcesCurrentlyBeingLoaded.remove();
            }
        }

    }

    private int doLoadBeanDefinitions(InputSource inputSource, Resource resource){
        return 0;

    }

    private int registerBeanDefinitions(Document doc, Resource resource) {
        return 0;
    }


}
