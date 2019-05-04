package com.cms.ioCreams.beans.factory.xml;

import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import com.cms.ioCreams.beans.factory.config.DefaultBeanDefinition;
import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;
import com.cms.ioCreams.core.io.support.EncodedResource;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.*;


public class DefaultBeanDefinitionReader implements BeanDefinitionReader {

    Logger logger = Logger.getLogger(DefaultBeanDefinitionReader.class);

    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded = new ThreadLocal<>();

    @Override
    public ResourceLoader getResourceLoader() {
        return null;
    }



    @Override
    public Map<String, BeanDefintion> loadBeanDefinitions(EncodedResource encodedResource) throws Exception {
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
                return doLoadBeanDefinitions(encodedResource.getResource());
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

    public Map<String, BeanDefintion> doLoadBeanDefinitions (Resource resource){

        Map<String, BeanDefintion> result = new HashMap<>();

        SAXReader reader = new SAXReader();

        InputStream inputStream = null;

        Document document = null;

        try {
            inputStream = resource.getInputStream();
            document = reader.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("开始解析配置文件Bean标签");
        String xpath = "//bean";
        List<Element> beanNodes = document.selectNodes(xpath);
        BeanDefinitionParserDelegate beanDefinitionParserDelegate = new BeanDefinitionParserDelegate();
        for (Element ele : beanNodes) {
            BeanDefintion bd = new DefaultBeanDefinition();
            bd = beanDefinitionParserDelegate.beanDefintionAttributeParse(bd, ele);
            logger.info("bean " + bd.getBeanName() + "注册成功");
            result.put(bd.getBeanName(), bd);
        }
        return result;
    }


}
