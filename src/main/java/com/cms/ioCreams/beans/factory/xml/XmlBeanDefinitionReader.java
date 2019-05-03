package com.cms.ioCreams.beans.factory.xml;

import com.cms.ioCreams.beans.factory.config.AbstractBeanDefinition;
import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import com.cms.ioCreams.beans.factory.config.DefaultBeanDefinition;

import com.cms.ioCreams.core.io.FileSystemResource;
import com.cms.ioCreams.core.io.Resource;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class XmlBeanDefinitionReader {

    Logger logger = Logger.getLogger(XmlBeanDefinitionReader.class);

    public static Map<String, BeanDefintion> getBeanConfig (Resource resource){

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

        String xpath = "//bean";
        List<Element> beanNodes = document.selectNodes(xpath);
        BeanDefinitionParserDelegate beanDefinitionParserDelegate = new BeanDefinitionParserDelegate();

        for (Element ele : beanNodes) {
            BeanDefintion bd = new DefaultBeanDefinition();
            bd = beanDefinitionParserDelegate.beanDefintionAttributeParse(bd, ele);
            result.put(bd.getBeanName(), bd);
        }
        return result;
    }

    public static void main(String[] args) {
        Resource resource = new FileSystemResource("beanfactory.xml");


        Map<String, BeanDefintion> map = getBeanConfig(resource);
        for (Entry<String, BeanDefintion> e : getBeanConfig(resource).entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue().toString());
        }
    }
}
