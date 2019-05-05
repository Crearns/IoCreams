# IoCreams

![](https://img.shields.io/badge/Version-0.1-green.svg)

仿造 Spring-Framework IoC容器实现了非常简易的控制反转容器


## V0.1版本
* 实现了路径获取资源
* 能够解析xml配置文件的``bean`` ``value`` ``ref`` ``property``标签的解析
* 实现了使用``<property>``可支持对象、字符串、简单数据类型的属性注入


## 版本缺陷
* 没有解决单例模式下循环依赖问题（在后续版本会加入）
* 属性注入方面使用了 apache 的 BeanUtil 框架包
* 不支持xml的验证
* 这个版本还是非常简陋，砍掉了很多功能


## TODO：
+ [ ] 实现三级缓存解决循环依赖问题
+ [ ] 优化属性注入功能
+ [ ] 提高容器的健壮性


## 快速开始

0. 导入lib文件夹下的jar包依赖

1. 先编写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
    <bean name="A" class="com.cms.ioCreams.A">
        <property name="name" value="Creams"></property>
        <property name="a" value="3"></property>
        <property name="b" ref="B"></property>
    </bean>
    <bean name="B" class="com.cms.ioCreams.B">
        <property name="name" value="Jinx"></property>
        <property name="age" value="15"></property>
    </bean>
</beans>
```

2. 初始化容器
```java
    public class testBeanFactory{
        public static void main(String[] args) {
            BeanFactory factory = new ClassPathXmlBeanFactory("beanfactory.xml");
        }
    }
```

3. 编写注入的Bean

A.java
```java

public class A {
    
    private String name;
    
    private double a;

    private B b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}

```

B.java
```java
public class B {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

```

4. 调用``getBean(String beanName)``获取bean
```java
public class testBeanFactory{
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

```

输出结果
```
[INFO ] 2019-05-04 17:10:58.249 [main] DefaultBeanDefinitionReader - 加载 BeanDefinition
[INFO ] 2019-05-04 17:10:58.284 [main] DefaultBeanDefinitionReader - 开始解析配置文件Bean标签
[INFO ] 2019-05-04 17:10:58.312 [main] BeanDefinitionParserDelegate - A的BeanName解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - A的class属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - A的scope属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - A的property属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] DefaultBeanDefinitionReader - bean A注册成功
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - B的BeanName解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - B的class属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - B的scope属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] BeanDefinitionParserDelegate - B的property属性解析
[INFO ] 2019-05-04 17:10:58.313 [main] DefaultBeanDefinitionReader - bean B注册成功
a.getName()：Creams
a.getA()：3.0
a.getB().getName()：Jinx
a.getB().getAge()：15

```



