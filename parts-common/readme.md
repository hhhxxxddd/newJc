核心公共包
----------

## 简介

主要封装一些通用公共类、工具类，如业务异常父类，对象父类等。

## 注意
本模块无法启动,而是被其他服务引用
并且没有父依赖,即没有继承父模块
所以依赖了一些核心代码

## 使用

进入应用目录

安装命令：`mvn install`

## 使用指南

### 应用引入

需要将编译生成的jar包安装到本地maven类进入引用使用。

pom.xml

|依赖|描述| 
|----|----|
|Spring.core|Spring框架的核心代码|
|Common.lang|强化java.lang包,如StringUtils等|
|Lombok|注释简化代码工具|
|Junit|测试工具|
```
<dependency>
    <groupId>com.jinchi</groupId>
    <artifactId>parts-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```