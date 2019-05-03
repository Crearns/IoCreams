package com.cms.ioCreams.beans.factory.xml;

import com.cms.ioCreams.beans.PropertyValue;
import com.cms.ioCreams.beans.factory.config.BeanDefintion;
import org.dom4j.Element;

import java.util.List;

import static com.cms.ioCreams.beans.factory.config.BeanDefintion.SCOPE_SINGLETON;


public class BeanDefinitionParserDelegate {



    public static final String AUTOWIRE_BY_NAME_VALUE = "byName";

    public static final String AUTOWIRE_BY_TYPE_VALUE = "byType";

    public static final String AUTOWIRE_CONSTRUCTOR_VALUE = "constructor";

    public static final String ID_ATTRIBUTE = "id";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String LAZY_INIT_ATTRIBUTE = "lazy-init";

    public static final String AUTOWIRE_ATTRIBUTE = "autowire";

    public static final String INIT_METHOD_ATTRIBUTE = "init-method";

    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public static final String FACTORY_METHOD_ATTRIBUTE = "factory-method";

    public static final String FACTORY_BEAN_ATTRIBUTE = "factory-bean";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";



    public BeanDefintion beanDefintionAttributeParse(BeanDefintion bd, Element ele){
        bd.setBeanName(ele.attributeValue("name"));
        nameParse(bd,ele);
        classParse(bd, ele);
        scopeParse(bd, ele);
        propertyParse(bd, ele);
        return bd;
    }

    public void nameParse(BeanDefintion bd, Element ele){
        bd.setBeanName(ele.attributeValue(NAME_ATTRIBUTE));
    }

    public void classParse(BeanDefintion bd, Element ele){
        bd.setClassName(ele.attributeValue(CLASS_ATTRIBUTE));
    }

    public void scopeParse(BeanDefintion bd, Element ele){
        String scope = ele.attributeValue(SCOPE_ATTRIBUTE);
        if (scope == null)
            scope = SCOPE_SINGLETON;
        bd.setScope(scope);
    }

    public void propertyParse(BeanDefintion bd, Element ele){
        List<Element> propNodes = ele.elements(PROPERTY_ELEMENT);
        if (propNodes != null){
            for (Element prop : propNodes) {
                //System.out.println(prop.attributeValue(NAME_ATTRIBUTE)+ prop.attributeValue(VALUE_ATTRIBUTE)+ prop.attributeValue(REF_ATTRIBUTE));
                PropertyValue pv = new PropertyValue(prop.attributeValue(NAME_ATTRIBUTE), prop.attributeValue(VALUE_ATTRIBUTE), prop.attributeValue(REF_ATTRIBUTE));
                bd.addProperty(pv);
            }
        }
    }
}
