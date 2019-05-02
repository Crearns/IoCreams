package com.cms.ioCreams.beans.factory.config;

import com.cms.ioCreams.beans.MutablePropertyValues;

public interface BeanDefintion {

    static final String SCOPE_SINGLETON = "singleton";

    static final String SCOPE_PROTOTYPE = "prototype";

    void setBeanClassName(String beanClassName);

    String getBeanClassName();

    void setScope();

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    boolean hasPropertyValues();

    MutablePropertyValues getPropertyValues();

}
