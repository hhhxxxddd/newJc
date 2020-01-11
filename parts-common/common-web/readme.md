WEB公共包
----------

## 简介

主要封装一些WEB开发用到的通用公共类、工具类，如公共web拦截器、web统一异常定义等。

## 使用

进入应用目录

安装命令：`mvn install`

## 使用指南

### 应用引入

需要将编译生成的jar包安装到本地maven类进入引用使用。

pom.xml

|依赖|描述|
|----|----|
|common-core|自己组件的核心依赖模块|
|springWeb/springWebMVC|定义了全局异常,所以需要结合spring的web包|
|mybatis|人人都爱mybatis(除了我)|
|mybatis-plus|简化mybatis开发工具|
|fasterxml|序列化反序列化工具,比如解析json|
|redis|缓存依赖|
|commons-pool|对象池,连接池的底层实现|
|spring-boot-starter-test|测试包集合|

```
<dependency>
    <groupId>com.jinchi</groupId>
    <artifactId>common-web</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```