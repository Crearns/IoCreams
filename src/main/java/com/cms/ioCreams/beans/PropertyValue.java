package com.cms.ioCreams.beans;


public class PropertyValue {

    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(PropertyValue propertyValue){
        this.name = propertyValue.name;
        this.value = propertyValue.value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }



}
