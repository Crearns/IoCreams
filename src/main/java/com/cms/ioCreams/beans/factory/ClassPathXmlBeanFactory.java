package com.cms.ioCreams.beans.factory;

import com.cms.ioCreams.beans.factory.config.BeanDefintion;

import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlBeanFactory implements BeanFactory {

    // 存放配置文件信息
    private Map<String, BeanDefintion> beanDefintionMap;
    // 存放bean对象的容器
    private Map<String, Object> context = new HashMap<>();


    public ClassPathXmlBeanFactory() {

    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }
}
