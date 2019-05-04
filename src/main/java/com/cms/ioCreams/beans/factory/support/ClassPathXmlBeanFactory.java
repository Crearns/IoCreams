package com.cms.ioCreams.beans.factory.support;

import com.cms.ioCreams.A;
import com.cms.ioCreams.B;
import com.cms.ioCreams.beans.PropertyValue;
import com.cms.ioCreams.beans.PropertyValues;
import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import com.cms.ioCreams.beans.factory.xml.BeanDefinitionReader;
import com.cms.ioCreams.beans.factory.xml.DefaultBeanDefinitionReader;
import com.cms.ioCreams.core.io.FileSystemResourceLoader;
import com.cms.ioCreams.core.io.Resource;
import com.cms.ioCreams.core.io.ResourceLoader;
import com.cms.ioCreams.core.io.support.EncodedResource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.cms.ioCreams.beans.factory.config.BeanDefintion.SCOPE_SINGLETON;

public class ClassPathXmlBeanFactory implements BeanFactory {

    // 存放配置文件信息
    private Map<String, BeanDefintion> beanDefintionMap;
    // 存放bean对象的容器
    private Map<String, Object> context = new HashMap<>();

    Logger logger = Logger.getLogger(ClassPathXmlBeanFactory.class);

    public ClassPathXmlBeanFactory() {

    }

    public ClassPathXmlBeanFactory(String path) throws Exception {
        BeanDefinitionReader beanDefinitionReader = new DefaultBeanDefinitionReader();
        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        this.beanDefintionMap = beanDefinitionReader.loadBeanDefinitions(new EncodedResource(resource));
    }


    @Override
    public Object getBean(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        BeanDefintion beanDefintion = this.beanDefintionMap.get(beanName);
        if (beanDefintion.getScope().equals(SCOPE_SINGLETON))
            if (context.containsKey(beanName))
                return context.get(beanName);
        Object bean = Class.forName(beanDefintion.getClassName()).newInstance();
        if (beanDefintion.hasPropertyValues())
            populate(bean, beanDefintion.getPropertyValues());
        context.put(beanName, bean);
        return bean;
    }

    public Object doGetBean(String beanName) {
        return null;
    }

    public void populate(Object bean, PropertyValues propertyValues) {
        for (PropertyValue pv :
                propertyValues.getPropertyValues()) {
            Map<String, Object> prop = new HashMap<>();
            if (pv.getValue() != null) {
                prop.put(pv.getName(), pv.getValue());
                try {
                    BeanUtils.populate(bean, prop);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else if (pv.getRef() != null) {
                Object ref = context.get(pv.getRef());
                if (ref == null) {
                    try {
                        ref = getBean(beanDefintionMap.get(pv.getRef()).getBeanName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                prop.put(pv.getName(), ref);
                try {
                    BeanUtils.populate(bean, prop);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            BeanFactory factory = new ClassPathXmlBeanFactory("beanfactory.xml");
            A a = (A) factory.getBean("A");
            System.out.println("a.getName()：" + a.getName());
            System.out.println("a.getA()：" + a.getA());
            System.out.println("a.getB().getName()：" + a.getB().getName());
            System.out.println("a.getB().getAge()：" + a.getB().getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
