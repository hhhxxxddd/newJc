核心公共包
----------

## 简介

主要封装一些通用公共类、工具类，如业务异常父类，对象父类等。

## 使用

进入应用目录

安装命令：`mvn install`

## 使用指南

### 应用引入

需要将编译生成的jar包安装到本地maven类进入引用使用。

pom.xml

|依赖|描述|
|----|----|
|swagger|接口文档和ui|
```
<dependency>
    <groupId>com.jinchi</groupId>
    <artifactId>common-core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```