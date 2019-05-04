package com.cms.ioCreams.beans.factory.support;

public interface BeanFactory {

    Object getBean(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

}
