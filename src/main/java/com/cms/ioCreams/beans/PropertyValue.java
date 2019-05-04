package com.cms.ioCreams.beans;


import com.sun.istack.internal.Nullable;

public class PropertyValue {

    private  String name;

    private  String value;

    private  String ref;


    public PropertyValue(String name, String value, String ref) {
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public PropertyValue(PropertyValue propertyValue){
        this.name = propertyValue.name;
        this.value = propertyValue.value;
        this.ref = propertyValue.ref;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getRef() {
        return ref;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public PropertyValue() {
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
