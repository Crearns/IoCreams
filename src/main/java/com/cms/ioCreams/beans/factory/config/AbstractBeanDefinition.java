package com.cms.ioCreams.beans.factory.config;

import com.cms.ioCreams.beans.MutablePropertyValues;
import com.cms.ioCreams.beans.PropertyValue;

public class AbstractBeanDefinition implements BeanDefintion {

    private MutablePropertyValues propertyValues = new MutablePropertyValues();

    private String beanName;

    private String className;

    private String scope = SCOPE_SINGLETON;

    @Override
    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    @Override
    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public void addProperty(PropertyValue pv) {
        propertyValues.addPropertyValue(pv);
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public boolean isSingleton() {
        return this.scope.equals(SCOPE_SINGLETON);
    }

    @Override
    public boolean isPrototype() {
        return this.scope.equals(SCOPE_PROTOTYPE);
    }

    @Override
    public boolean hasPropertyValues() {
        return propertyValues != null && !propertyValues.isEmpty();
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }


    @Override
    public String toString() {
        return "AbstractBeanDefinition{" +
                "propertyValues=" + propertyValues.toString() +
                ", beanName='" + beanName + '\'' +
                ", className='" + className + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
