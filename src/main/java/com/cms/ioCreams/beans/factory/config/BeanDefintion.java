package com.cms.ioCreams.beans.factory.config;

import com.cms.ioCreams.beans.MutablePropertyValues;
import com.cms.ioCreams.beans.PropertyValue;

public interface BeanDefintion {

    static final String SCOPE_SINGLETON = "singleton";

    static final String SCOPE_PROTOTYPE = "prototype";

    void setClassName(String beanClassName);

    String getClassName();

    void setScope(String scope);

    String getBeanName();

    void setBeanName(String beanName);

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    boolean hasPropertyValues();

    MutablePropertyValues getPropertyValues();

    void setPropertyValues(MutablePropertyValues propertyValues);

    void addProperty(PropertyValue pv);

}
