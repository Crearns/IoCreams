package com.cms.ioCreams.beans;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MutablePropertyValues implements PropertyValues {

    private final List<PropertyValue> propertyValueList;

    private Set<String> processedProperties;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    public MutablePropertyValues(PropertyValues original) {
        // We can optimize this because it's all new:
        // There is no replacement of existing property values.
        if (original != null) {
            PropertyValue[] pvs = original.getPropertyValues();
            this.propertyValueList = new ArrayList<>(pvs.length);
            for (PropertyValue pv : pvs) {
                this.propertyValueList.add(new PropertyValue(pv));
            }
        }
        else {
            this.propertyValueList = new ArrayList<>(0);
        }
    }

    public MutablePropertyValues add(String propertyName, String propertyValue, String ref) {
        addPropertyValue(new PropertyValue(propertyName, propertyValue, ref));
        return this;
    }

    public MutablePropertyValues addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                setPropertyValueAt(pv, i);
                return this;
            }
        }
        this.propertyValueList.add(pv);
        return this;
    }

    public void setPropertyValueAt(PropertyValue pv, int i) {
        this.propertyValueList.set(i, pv);
    }



    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList =
                (propertyValueList != null ? propertyValueList : new ArrayList<>());
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }


    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }


    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    @Override
    public boolean contains(String propertyName) {
        return (getPropertyValue(propertyName) != null ||
                (this.processedProperties != null && this.processedProperties.contains(propertyName)));
    }

    @Override
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (PropertyValue propertyValue : this.propertyValueList) {
            stringBuffer.append(propertyValue.toString() + "--");
        }
        return stringBuffer.toString();
    }
}
