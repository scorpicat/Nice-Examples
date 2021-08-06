###1.搭建步骤

0.搭建数据库

```java
CREATE DATABASE `mybatis`;

USE `mybatis`;

CREATE TABLE `user`(
  `id` INT(20) NOT NULL PRIMARY KEY,
  `name` VARCHAR(30) DEFAULT NULL,
  `pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`name`,`pwd`) VALUES 
(1,'狂神','123456'),
(2,'张三','123456'),
(3,'李四','123890')
```


1. 新建一个普通的maven项目作为父项目【注意maven环境】

2. 删除src目录【搭建父工程】

3. 导入maven依赖：mybatis、数据库驱动和junit即可

### 2.创建一个模块

- 编写mybatis的核心配置文件【src/resources/mybatis-config.xml】
- 编写utils包下mybatis工具类：此处的sqlSession在ssm整合项目中将配置在Spring配置文件中
  
  
### 3.配置mybatis配置文件mybatis-config.xml
- configuration标签下的子标签严格遵守标签顺序
- <typeAlias>标签用来给***实体类***起别名的，可以在实体类中使用@Alias注解替代
- <mappers>标签下子标签使用<mapper>且属性配置为class或标签使用<package>时，Dao层下的Dao要改名和Mapper文件名保持一致，如UserMapper.java与UserMapper.xml
### 4.编写代码
- 编写dao接口及对应的mapper配置文件
- 编写pojo实体对象类
- 在test中测试即可
### 5.mapper.xml的注意事项
- 返回类型一般有resultType和resultMap,在实体类的属性与表字段完全对应时使用resultType，不一致需要使用resultMap